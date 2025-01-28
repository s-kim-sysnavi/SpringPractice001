package searchman.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import searchman.entity.Shain;
import searchman.entity.User;
import searchman.repository.UserRepository;
import searchman.service.CustomUserDetailsService;
import searchman.service.ShainService;

@Controller
public class ShainController {
	@Autowired
	private ShainService shainService;

	private UserRepository userRepository;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	public ShainController(ShainService shainService, UserRepository userRepository) {
		this.shainService = shainService;
		this.userRepository = userRepository;
	}

	@GetMapping("/login")
	public String login(@RequestParam(required = false) String message, Model model) {
		return "login";
	}

	//Test用
	//	@PostMapping("/login_success")
	//	public String login_success(
	//			@RequestParam("username") String username,
	//			@RequestParam("password") String password) {
	//
	//		boolean isAuthenticated = authenticateUser(username, password);
	//		if (isAuthenticated) {
	//			return "redirect:/top";
	//		} else {
	//			return "redirect:/login?error";
	//		}
	//	}

	//	private boolean authenticateUser(String username, String password) {
	//
	//		return "username".equals(username) && "password".equals(password);
	//	}

	@GetMapping({ "/register" })
	public String register(@RequestParam(required = false) String message, Model model) {
		if (message != null) {
			model.addAttribute("message", message);
		}
		return "register";
	}

	@PostMapping({ "/register_success" })
	public String registerUser(@RequestParam String username, @RequestParam String password, @RequestParam String role,
			RedirectAttributes redirectAttributes, @ModelAttribute Shain request) {

		String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		Pattern pattern = Pattern.compile(emailRegex);

		try {
			if (!pattern.matcher(username).matches()) {
				redirectAttributes.addFlashAttribute("message", "メールアドレスの形式が正しくありません。");
				redirectAttributes.addFlashAttribute("alertType", "error");
				return "redirect:/register";
			}

			User user = this.customUserDetailsService.registerUser(username, password, role);
			request.setUserId(user.getId());
			Shain shain = this.shainService.makeShain(request);
			this.shainService.insertShain(shain);
			redirectAttributes.addFlashAttribute("message", "会員登録に成功しました。");
			redirectAttributes.addFlashAttribute("alertType", "success");
			return "redirect:/login";
		} catch (IllegalArgumentException e) {
			redirectAttributes.addFlashAttribute("message", "既に登録しているアカウントです。");
			redirectAttributes.addFlashAttribute("alertType", "error");
			return "redirect:/register";
		}
	}

	@GetMapping({ "/top" })
	public String top(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
				.map(grantedAuthority -> grantedAuthority.getAuthority()).findFirst().orElse(null);
		Long currentUserId = this.customUserDetailsService.getLoggedInUserId();
		Shain shain = this.shainService.findByShainId(currentUserId);
		model.addAttribute("shain", shain);
		model.addAttribute("currentUserId", currentUserId);
		model.addAttribute("username", username);
		model.addAttribute("role", role);
		return "top";
	}

	@GetMapping({ "/" })
	public String main(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
				.map(grantedAuthority -> grantedAuthority.getAuthority()).findFirst().orElse(null);
		Long currentUserId = this.customUserDetailsService.getLoggedInUserId();
		Shain shain = this.shainService.findByShainId(currentUserId);
		model.addAttribute("shain", shain);
		model.addAttribute("currentUserId", currentUserId);
		model.addAttribute("username", username);
		model.addAttribute("role", role);
		return "top";
	}

	@GetMapping({ "/index" })
	public String index(Model model, Model model2, Model model3) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
				.map(grantedAuthority -> grantedAuthority.getAuthority()).findFirst().orElse(null);
		Long currentUserId = this.customUserDetailsService.getLoggedInUserId();
		List<Shain> shainList = this.shainService.findAll();
		List<User> users = this.customUserDetailsService.getAllUsers();
		model2.addAttribute("users", users);
		model3.addAttribute("currentUserId", currentUserId);
		model3.addAttribute("username", username);
		model3.addAttribute("role", role);
		model.addAttribute("shainList", shainList);
		return "index";
	}

	@GetMapping({ "/update_confirm" })
	public String update(@RequestParam("userId") Long userId, Model model, Model model2) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
				.map(grantedAuthority -> grantedAuthority.getAuthority()).findFirst().orElse(null);
		Long currentUserId = this.customUserDetailsService.getLoggedInUserId();
		Shain shain = this.shainService.findByShainId(userId);
		Optional<User> user = this.userRepository.findById(userId);
		user.ifPresent(value -> model2.addAttribute("user", value));
		model.addAttribute("currentUserId", currentUserId);
		model.addAttribute("username", username);
		model.addAttribute("role", role);
		model.addAttribute("shain", shain);
		if (!"ADMIN".equals(role))
			if (role != "ADMIN" && currentUserId != shain.getUserId())
				return "redirect:/top";
		return "update_confirm";

		//テスト用
		//		if (!"ROLE_ADMIN".equals(role))
		//			if (role != "ROLE_ADMIN" && currentUserId != shain.getUserId())
		//				return "redirect:/top";
		//		return "update";
	}

	@PostMapping({ "/update" })
	public String updateSuccess(@ModelAttribute Shain request, @RequestParam("username") String username,
			@RequestParam("role") String role, RedirectAttributes redirectAttributes) {
		Shain shain = this.shainService.makeShain(request);
		this.customUserDetailsService.updateUserRole(username, role);
		this.shainService.updateShain(shain);
		redirectAttributes.addFlashAttribute("message", "プロフィールの更新ができました。");
		redirectAttributes.addFlashAttribute("alertType", "success");
		return "redirect:/index";
	}

	@GetMapping({ "/delete_confirm" })
	public String delete(@RequestParam("userId") Long userId, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
				.map(grantedAuthority -> grantedAuthority.getAuthority()).findFirst().orElse(null);
		Long currentUserId = this.customUserDetailsService.getLoggedInUserId();
		Shain shain = this.shainService.findByShainId(userId);
		Optional<User> user = this.userRepository.findById(userId);
		user.ifPresent(value -> model.addAttribute("user", value));
		model.addAttribute("currentUserId", currentUserId);
		model.addAttribute("username", username);
		model.addAttribute("role", role);
		model.addAttribute("shain", shain);
		if (!"ADMIN".equals(role))
			return "redirect:/top";
		return "delete_confirm";

		//テスト用
		//		if (!"ROLE_ADMIN".equals(role))
		//			return "redirect:/top";
		//		return "delete";
	}

	@PostMapping({ "/delete" })
	public String deleteSuccess(@RequestParam("userId") Long userId, RedirectAttributes redirectAttributes) {
		this.shainService.deleteShain(userId);
		Optional<User> user = this.userRepository.findById(userId);
		Objects.requireNonNull(this.userRepository);
		user.ifPresent(this.userRepository::delete);
		redirectAttributes.addFlashAttribute("message", "該当社員の情報が削除できました。");
		redirectAttributes.addFlashAttribute("alertType", "success");
		return "redirect:/index";
	}

	@GetMapping({ "/profile_upload" })
	public String profile(@RequestParam("userId") Long userId, Model model, Model model2) {
		Shain shain = this.shainService.findByShainId(userId);
		model.addAttribute("shain", shain);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
				.map(grantedAuthority -> grantedAuthority.getAuthority()).findFirst().orElse(null);
		Long currentUserId = this.customUserDetailsService.getLoggedInUserId();
		Optional<User> user = this.userRepository.findById(userId);
		user.ifPresent(value -> model2.addAttribute("user", value));
		model.addAttribute("currentUserId", currentUserId);
		model.addAttribute("username", username);
		model.addAttribute("role", role);
		model.addAttribute("shain", shain);
		if (!"ADMIN".equals(role)) {
			if (role != "ADMIN" && currentUserId != shain.getUserId()) {
				return "redirect:/top";
			}
		}

		//テスト用
		//		if (!"ROLE_ADMIN".equals(role)) {
		//			if (role != "ROLE_ADMIN" && currentUserId != shain.getUserId()) {
		//				return "redirect:/top";
		//			}
		//		}
		return "profile_upload";
	}

	@Value("${profile.upload.path}") // ファイルパスの保存
	private String uploadPath;

	@PostMapping("/profile_success")
	public String profileSuccess(@RequestParam("userId") Long userId,
			@RequestParam("profileImage") MultipartFile file, Model model, RedirectAttributes redirectAttributes) {

		Shain shain = shainService.findByShainId(userId);
		model.addAttribute("shain", shain);

		System.out.println(shain.getUserId());

		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "ファイルが選択されていません。");
			redirectAttributes.addAttribute("userId", userId);
			return "redirect:/update_confirm";
		}

		try {
			Path path = Paths.get(uploadPath, file.getOriginalFilename());
			Files.write(path, file.getBytes());
			//			// 保存パス生成
			String fileName = file.getOriginalFilename();

			// DBに保存
			shainService.updateProfileImage(userId, fileName);

			redirectAttributes.addFlashAttribute("message", "アップロード成功");
		} catch (IOException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "エラー発生");
		}
		redirectAttributes.addAttribute("userId", userId);
		return "redirect:/update_confirm";
	}
}
