package com.bootcamp.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(
				"SELECT username, password, enabled FROM user WHERE username = ?")
		.authoritiesByUsernameQuery(
		"SELECT user.username, authorities.authority as authorities " +
		"FROM user, authorities " +
		"WHERE user.username = ? AND user.username = authorities.username");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/").hasAnyRole("ADMIN", "USER").antMatchers("/user").hasRole("USER")
				.antMatchers("/message").hasRole("ADMIN").and().formLogin().permitAll().and().logout().permitAll().and()
				.exceptionHandling().accessDeniedPage("/access-denied");
	}
}