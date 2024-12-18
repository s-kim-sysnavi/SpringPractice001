package searchman.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	//	@Autowired
	//	private UserRepository userRepository;
	//	@Autowired
	//	private PasswordEncoder passwordEncoder;

	public ShainController(ShainService shainService, UserRepository userRepository) {
		this.shainService = shainService;
		this.userRepository = userRepository;
	}

	@GetMapping("/login")
	public String login() {
		return "login"; // login.jsp
	}

	@GetMapping("/register")
	public String Register() {
		return "register"; // register.jsp
	}

	@PostMapping("/register")
	public String registerUser(@RequestParam String username, @RequestParam String password,
			@RequestParam String role, Model model, @ModelAttribute Shain request) {
		try {
			User user = customUserDetailsService.registerUser(username, password, role); // 会員登録のロジック

			request.setUserId(user.getId());
			Shain shain = shainService.makeShain(request);
			shainService.insertShain(shain);

			model.addAttribute("message", username + "様、登録ありがとうございます。");

			//			Shain shain = shainService.makeShain(request);
			//		}
			//		;

			//DBに挿入
			//			shainService.insertShain(shain);

			return "redirect:/login"; // ログイン画面にリダイレクト
		} catch (IllegalArgumentException e) {
			return "redirect:/register?error"; // エラー
		}

	}

	@GetMapping("/top")
	public String top(Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
				.map(grantedAuthority -> grantedAuthority.getAuthority())
				.findFirst().orElse(null);

		Long currentUserId = customUserDetailsService.getLoggedInUserId();

		model.addAttribute("currentUserId", currentUserId);
		model.addAttribute("username", username);
		model.addAttribute("role", role);
		return "top";
	}

	//	@GetMapping("/profile")
	//	public String profile() {
	//		return "profile";
	//	}

	//	@GetMapping("/")
	//	public String main(Model model) {
	//
	//		return "redirect:/top";
	//	}

	@GetMapping("/")
	public String main(Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
				.map(grantedAuthority -> grantedAuthority.getAuthority())
				.findFirst().orElse(null);
		Long currentUserId = customUserDetailsService.getLoggedInUserId();

		model.addAttribute("currentUserId", currentUserId);
		model.addAttribute("username", username);
		model.addAttribute("role", role);
		return "top";
	}

	//	@GetMapping("/")
	//	public String main(Model model) {
	//
	//		// リスト取得
	//		List<Shain> shainList = shainService.findAll();
	//
	//		// JSPに渡す
	//		model.addAttribute("shainList", shainList);
	//		// JSPに転送
	//		return "redirect:/index";
	//	}

	@GetMapping("/index")
	public String index(Model model, Model model2, Model model3) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
				.map(grantedAuthority -> grantedAuthority.getAuthority())
				.findFirst().orElse(null);
		Long currentUserId = customUserDetailsService.getLoggedInUserId();

		// リスト取得
		List<Shain> shainList = shainService.findAll();
		//		System.out.println(shainList.get(6).getUserId());
		//
		//		System.out.println("ログイン" + currentUserId);
		//		System.out.println("ログイン" + role);

		List<User> users = customUserDetailsService.getAllUsers();

		model2.addAttribute("users", users);

		// JSPに渡す
		model3.addAttribute("currentUserId", currentUserId);
		model3.addAttribute("username", username);
		model3.addAttribute("role", role);

		// JSPに渡す
		model.addAttribute("shainList", shainList);
		// JSPに転送
		return "index";
	}

	//	@GetMapping("/insert")
	//	public String insert(Model model, Model model2) {
	//		//ログインユーザ-情報
	//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	//		String username = authentication.getName();
	//		String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
	//				.map(grantedAuthority -> grantedAuthority.getAuthority())
	//				.findFirst().orElse(null);
	//		Long currentUserId = customUserDetailsService.getLoggedInUserId();
	//
	//		model.addAttribute("currentUserId", currentUserId);
	//		model.addAttribute("username", username);
	//		model.addAttribute("role", role);
	//
	//		List<User> users = customUserDetailsService.getAllUsers();
	//
	//		model2.addAttribute("users", users);
	//
	//		// JSPに転送
	//		return "insert";
	//	}

	@PostMapping("/insert")
	public String insert(@ModelAttribute Shain request) {
		//, @RequestParam("userIdEmail") String value
		//ログインユーザ-情報
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
				.map(grantedAuthority -> grantedAuthority.getAuthority())
				.findFirst().orElse(null);
		Long currentUserId = customUserDetailsService.getLoggedInUserId();

		//		パラメータ値から社員作成
		//		if ("ADMIN".equals(role)) {
		//			String[] class  = value.split(":");
		//			int userId = Integer.parseInt(values[0]);
		//			String email = values[1];
		//
		//			Shain shain = shainService.makeShain2(request, userId, email);
		//		} else {
		Shain shain = shainService.makeShain(request);
		//		}
		//		;

		//DBに挿入
		shainService.insertShain(shain);

		// リダイレクト
		return "redirect:/index";

	}

	@GetMapping("/update")
	public String update(@RequestParam("userId") Long userId, Model model, Model model2) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
				.map(grantedAuthority -> grantedAuthority.getAuthority())
				.findFirst().orElse(null);
		Long currentUserId = customUserDetailsService.getLoggedInUserId();

		//対象データを取得
		Shain shain = shainService.findByShainId(userId);
		Optional<User> user = userRepository.findById(userId);
		//		System.out.println(shain.getUserId());
		//		System.out.println("ログイン" + currentUserId);
		//		System.out.println("ログイン" + role);

		user.ifPresent(value -> model2.addAttribute("user", value));
		// JSPに渡す
		model.addAttribute("currentUserId", currentUserId);
		model.addAttribute("username", username);
		model.addAttribute("role", role);
		model.addAttribute("shain", shain);

		//		Integer userIdif = shain.getUserId();
		//		System.out.println(userIdif );
		//		System.out.println("AAAA" );

		if ("ADMIN".equals(role)) {
			//			System.out.println("成功");
		} else if (role != "ADMIN" && currentUserId != shain.getUserId()) {
			return "redirect:/top";
		}

		// JSPに転送
		return "update";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute Shain request,
			@RequestParam("username") String username,
			@RequestParam("role") String role) {

		//		,		@RequestParam(value = "profileImage", required = false) MultipartFile profileImage

		//パラメータ値から社員作成
		Shain shain = shainService.makeShain(request);

		// 画像保存
		//		if (profileImage != null && !profileImage.isEmpty()) {
		//			try {
		//				String uploadDir = "src/main/resources/static/img/";
		//				//				= new File("src/main/resources/img/").getAbsolutePath() + "/";
		//				String originalFilename = profileImage.getOriginalFilename();
		//				String uniqueFilename = UUID.randomUUID().toString() + "_" + originalFilename;
		//				String filePath = uploadDir + uniqueFilename;
		//
		//				System.out.println(uploadDir);
		//				System.out.println(filePath);
		//
		//				File uploadDirFile = new File(uploadDir);
		//				if (!uploadDirFile.exists()) {
		//					uploadDirFile.mkdirs(); // 
		//				}
		//				profileImage.transferTo(new File(filePath));
		//				shain.setProfileImage(filePath);
		//			} catch (IOException e) {
		//				e.printStackTrace();
		//
		//				return "redirect:/update";
		//			}
		//		}
		customUserDetailsService.updateUserRole(username, role);

		//DB更新
		shainService.updateShain(shain);

		// リダイレクト
		return "redirect:/index";
	}

	@GetMapping("/delete")
	public String delete(
			@RequestParam("userId") Long userId,
			Model model) {

		//対象データを取得
		Shain shain = shainService.findByShainId(userId);

		// JSPに渡す
		model.addAttribute("shain", shain);

		// JSPに転送
		return "delete";
	}

	@PostMapping("/delete")
	public String delete(
			@RequestParam("userId") Long userId) {

		//DBから削除
		shainService.deleteShain(userId);
		Optional<User> user = userRepository.findById(userId);
		user.ifPresent(userRepository::delete);

		// リダイレクト
		return "redirect:/index";
	}

	//	@GetMapping("/copy")
	//	public String copy(@RequestParam("userId") int userId,
	//			Model model) {
	//
	//		//対象データを取得
	//		Shain shain = shainService.findByShainId(userId);
	//
	//		// JSPに渡す
	//		model.addAttribute("shain", shain);
	//		// JSPに転送
	//		return "copy";
	//	}
	//
	//	@PostMapping("/copy")
	//	public String copy(@ModelAttribute Shain request) {
	//
	//		//パラメータ値から社員作成
	//		Shain shain = shainService.makeShain(request);
	//
	//		//DBに挿入
	//		shainService.copyShain(shain);
	//
	//		// リダイレクト
	//		return "redirect:/index";
	//
	//	}

}
