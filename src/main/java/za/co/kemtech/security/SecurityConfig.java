package za.co.kemtech.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import za.co.kemtech.service.MyUserDetailsService;

@Component
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	public SecurityConfig(MyUserDetailsService myUserDetailsService) {
		super();
		this.myUserDetailsService = myUserDetailsService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/").permitAll()
			.antMatchers("/user").hasRole("USER")
			.antMatchers("/admin").hasRole("ADMIN")
			.anyRequest()
			.authenticated()
			.and()
			.formLogin()
//				.loginPage("/login")
				.defaultSuccessUrl("/user");
			
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	private DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(myUserDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
}
