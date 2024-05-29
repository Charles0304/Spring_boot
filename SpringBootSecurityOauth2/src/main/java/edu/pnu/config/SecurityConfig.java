package edu.pnu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(security->security
				.requestMatchers("/member/**").authenticated()
				.requestMatchers("/manager/**").authenticated()
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll());
		
		http.csrf(cf->cf.disable());
		
		http.formLogin(form->form.loginPage("/login")
				.defaultSuccessUrl("/loginSuccess",true));
		http.exceptionHandling(ex->ex.accessDeniedPage("/accessDenied"));
		
		http.logout(logout->logout
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.logoutSuccessUrl("/login"));
		http.headers(hr->hr.frameOptions(fo->fo.disable()));
		http.oauth2Login(oauth2->oauth2
				.loginPage("/login")
				.defaultSuccessUrl("/loginSuccess",true));
		return http.build();
	}
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
//	@Autowired
//	public void authenticate(AuthenticationManagerBuilder auth)throws Exception{
//		auth.inMemoryAuthentication()
//		.withUser("manager")
//		.password("{noop}1234")
//		.roles("MANAGER");
//		auth.inMemoryAuthentication()
//		.withUser("admin")
//		.password("{noop}1234")
//		.roles("ADMIN");
//	}
}
