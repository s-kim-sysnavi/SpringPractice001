package searchman.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import searchman.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	private final UserRepository userRepository;

	public SecurityConfig(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Bean
	public CustomUserDetailsService customUserDetailsService() {
		return new CustomUserDetailsService(userRepository);
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(csrf -> csrf
						.ignoringRequestMatchers("/logout"))

				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/delete", "/copy").hasAuthority("ADMIN") // ADMINユーザーのみアクセス可能
						.requestMatchers("login", "/WEB-INF/**", "/register").permitAll() // カスタマイズしたページの許可

						.anyRequest().authenticated() // その他は認証必要
				)
				//				.authorizeHttpRequests((authorize) -> authorize
				//						.requestMatchers("/index").hasAuthority("ADMIN")
				//						.anyRequest().authenticated())
				.formLogin(form -> form
						.loginPage("/login") // ログインページURLを指定
						.loginProcessingUrl("/login") //ログイン処理 URL
						.defaultSuccessUrl("/top", true) // ログインした後の URL
						.failureUrl("/login?error=true") // ログイン失敗時
						//	.permitAll() // ログインにアクセス許容
						.usernameParameter("username") // フォームのユーザー名field
						.passwordParameter("password") // フォームのパスワードfield
				)

				.logout(logout -> logout
						.logoutUrl("/logout") // ログアウトURL
						.permitAll()// ログアウトにアクセス許容 
						.logoutSuccessUrl("/login?logout")
						.invalidateHttpSession(true));

		return http.build(); // httpSecurity オブジェクトを戻す
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}