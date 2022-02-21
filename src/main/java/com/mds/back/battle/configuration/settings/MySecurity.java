package com.mds.back.battle.configuration.settings;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class MySecurity extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.sessionManagement(cust ->
		// cust.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/").permitAll();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}
}
