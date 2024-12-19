package searchman.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import searchman.entity.User;
import searchman.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	//ユーザー新規登録
	public User registerUser(String username, String password, String role) {
		// ユーザー名重複確認
		if (userRepository.findByUsername(username).isPresent()) {
			throw new IllegalArgumentException("既に登録しているアカウント名です。");
		}

		// ユーザーの生成
		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		user.setRole(role);

		// ユーザーを登録
		System.out.println("User saved: " + user.getUsername());
		System.out.println(user.getRole());
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username) // usernameでユーザー照し合せ
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

		return new org.springframework.security.core.userdetails.User(
				user.getUsername(),
				user.getPassword(),
				AuthorityUtils.createAuthorityList(user.getRole()));
	}

	public String checkUserRoles() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getAuthorities().toString();
	}

	public Long getLoggedInUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated()) {
			throw new IllegalStateException("로그인된 사용자가 없습니다.");
		}

		// 241212
		Object principal = authentication.getPrincipal();
		if (principal instanceof org.springframework.security.core.userdetails.User) {
			String username = ((org.springframework.security.core.userdetails.User) principal).getUsername();

			return userRepository.findByUsername(username)
					.map(User::getId)
					.orElseThrow(() -> new UsernameNotFoundException("ユーザー情報が保存されています。"));
		}

		throw new IllegalStateException("認証情報が間違っています。");
	}

	public List<User> getAllUsers() {
		return userRepository.findAll(); // userすべて取得
	}

	public void updateUserRole(String username, String role) {
		// username
		Optional<User> optionalUser = userRepository.findByUsername(username);

		//		if (optionalUser.isPresent()) {
		User user = optionalUser.get();

		//			if ("USER".equals(user.getRole())) {
		user.setRole(role); // role 
		userRepository.save(user); // 
		//			}
		//		} else {
		//			throw new RuntimeException("User not found with username: " + username);
		//		}
	}
}
