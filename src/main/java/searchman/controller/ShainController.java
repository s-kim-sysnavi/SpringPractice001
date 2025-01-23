package searchman.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
	public String login() {
		//		ModelAndView modelAndView = new ModelAndView("login");
		//		modelAndView.addObject("message", "Welcome to the login page!");
		//		return modelAndView;
		return "login";
	}

	@PostMapping("/login")
	public String login(
			@RequestParam("username") String username,
			@RequestParam("password") String password) {

		boolean isAuthenticated = authenticateUser(username, password);
		if (isAuthenticated) {
			return "redirect:/top";
		} else {
			return "redirect:/login?error";
		}
	}

	private boolean authenticateUser(String username, String password) {

		return "username".equals(username) && "password".equals(password);
	}

	//	public String login(Model model,View view) {
	//		model.addAttribute("message", "Welcome to the login page!");
	//		return "login";
	//	}

	@GetMapping({ "/register" })
	public String register() {
		return "register";
	}

	@PostMapping({ "/register" })
	public String registerUser(@RequestParam String username, @RequestParam String password, @RequestParam String role,
			Model model, @ModelAttribute Shain request) {
		try {
			User user = this.customUserDetailsService.registerUser(username, password, role);
			request.setUserId(user.getId());
			Shain shain = this.shainService.makeShain(request);
			this.shainService.insertShain(shain);
			model.addAttribute("message", username + "");
			return "redirect:/login";
		} catch (IllegalArgumentException e) {
			return "redirect:/register?error";
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

	@PostMapping({ "/insert" })
	public String insert(@ModelAttribute Shain request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
				.map(grantedAuthority -> grantedAuthority.getAuthority()).findFirst().orElse(null);
		Long currentUserId = this.customUserDetailsService.getLoggedInUserId();
		Shain shain = this.shainService.makeShain(request);
		this.shainService.insertShain(shain);
		return "redirect:/index";
	}

	@GetMapping({ "/update" })
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
		return "update";
		//		if (!"ROLE_ADMIN".equals(role))
		//			if (role != "ROLE_ADMIN" && currentUserId != shain.getUserId())
		//				return "redirect:/top";
		//		return "update";
	}

	@PostMapping({ "/update" })
	public String update(@ModelAttribute Shain request, @RequestParam("username") String username,
			@RequestParam("role") String role) {
		Shain shain = this.shainService.makeShain(request);
		this.customUserDetailsService.updateUserRole(username, role);
		this.shainService.updateShain(shain);
		//		this.shainService.updateShain(shain);
		return "redirect:/index";
	}

	@GetMapping({ "/delete" })
	public String delete(@RequestParam("userId") Long userId, Model model) {
		//		Shain shain = this.shainService.findByShainId(userId);
		//		model.addAttribute("shain", shain);
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
		return "delete";
		//		if (!"ROLE_ADMIN".equals(role))
		//			return "redirect:/top";
		//		return "delete";
	}

	@PostMapping({ "/delete" })
	public String delete(@RequestParam("userId") Long userId) {
		this.shainService.deleteShain(userId);
		Optional<User> user = this.userRepository.findById(userId);
		Objects.requireNonNull(this.userRepository);
		user.ifPresent(this.userRepository::delete);
		return "redirect:/index";
	}

	@GetMapping({ "/profile" })
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
		return "profile";
	}

	//	@PostMapping({ "/profile" })
	//	public String profile(@RequestParam("userId") Long userId, Model model) {
	//
	//		//対象データを取得
	//		Shain shain = shainService.findByShainId(userId);
	//		//		Optional<User> user = userRepository.findById(userId);
	//
	//		// JSPに渡す
	//
	//		model.addAttribute("shain", shain);
	//
	//		return "profile";
	//	}

	@Value("${profile.upload.path}") // ファイルパスの保存
	private String uploadPath;

	@PostMapping("/profile")
	public String profile(@RequestParam("userId") Long userId,
			@RequestParam("profileImage") MultipartFile file, Model model, RedirectAttributes redirectAttributes) {

		Shain shain = shainService.findByShainId(userId);
		model.addAttribute("shain", shain);

		System.out.println(shain.getUserId());

		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "ファイルが選択されていません。");
			redirectAttributes.addAttribute("userId", userId);
			return "redirect:/update";
		}

		try {
			Path path = Paths.get(uploadPath, file.getOriginalFilename());
			Files.write(path, file.getBytes());
			//			// 保存パス生成
			String fileName = file.getOriginalFilename();
			//			String filePath = uploadPath + File.separator + fileName;
			//
			//			// ファイルを保存
			//			File dest = new File(filePath);
			//			file.transferTo(dest);

			// DBに保存
			shainService.updateProfileImage(userId, fileName);

			redirectAttributes.addFlashAttribute("message", "アップロード成功");
		} catch (IOException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "エラー発生");
		}
		redirectAttributes.addAttribute("userId", userId);
		return "redirect:/update";
	}
}
