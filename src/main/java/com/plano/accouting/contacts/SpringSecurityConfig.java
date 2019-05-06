package com.plano.accouting.contacts;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	// Create 2 users for demo
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER").and()
			.withUser("admin").password("{noop}password").roles("ADMIN");

	}

	// Secure the endpoint with HTTP Basic authentication
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
				// HTTP Basic authentication
				.httpBasic().and().authorizeRequests()
				.antMatchers(HttpMethod.GET, "/contacts/**").hasRole("USER")
				.antMatchers(HttpMethod.POST, "/contacts").hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT, "/contacts/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.OPTIONS, "/contacts/**").permitAll()
				.antMatchers(HttpMethod.DELETE, "/contacts/**").hasRole("ADMIN")
				.and().csrf().disable()
				.formLogin().disable();
	}
}
