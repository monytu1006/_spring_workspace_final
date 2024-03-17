package com.myfinal.www.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.myfinal.www.security.CustomAuthMemberService;
import com.myfinal.www.security.LoginFailureHandler;
import com.myfinal.www.security.LoginSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public PasswordEncoder bcPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new LoginSuccessHandler();
	}
	
	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new LoginFailureHandler();
	}
	
	@Bean
	public UserDetailsService customUserService() {
		return new CustomAuthMemberService();
	}
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserService())
		.passwordEncoder(bcPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
			http.authorizeRequests()
			.antMatchers("/member/list", "/member/registerBlackList", "/member/cancelBlackList")
			.hasAnyRole("ADMIN")
			.antMatchers("/nmboard/**", "/comment/**", "/upload/**")
			.hasAnyRole("ADMIN", "USER")
			.antMatchers("/anboard/**", "/cmboard/**")
			.hasAnyRole("ADMIN", "USER", "BlackList")
			.antMatchers("/","/resources/**", "/member/**","/member/register", "/member/login", "/member/logout", "/member/detail", "/member/modify", "/member/delete")
			.permitAll()
		    .anyRequest().authenticated();
		http.formLogin()
		.usernameParameter("email")
		.passwordParameter("pwd")
		.loginPage("/member/login")
		.successHandler(authenticationSuccessHandler())
		.failureHandler(authenticationFailureHandler());
		http.logout()
		.logoutUrl("/member/logout")
		.invalidateHttpSession(true)
		.deleteCookies("JSESSIONID")
		.logoutSuccessUrl("/");
	}
}
