/**
 * 
 */
package com.sample.security.authorization;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author fabio.moriguchi
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {

	private static final String ROLE_ADMIN = "ROLE_ADMIN";
	private static final String ROLE_USER = "ROLE_USER";
	private static final String ROLE_SAYAJINS = "SAYAJINS";
	private static final String ROLE_NINJAS = "NINJAS";
	private static final String DEFAULT_PASS = "111111";

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication()
				.withUser("goku").password(DEFAULT_PASS).authorities(ROLE_SAYAJINS).and()
				.withUser("vegeta").password(DEFAULT_PASS).authorities(ROLE_SAYAJINS).and()
				.withUser("jaspion").password(DEFAULT_PASS).authorities(ROLE_NINJAS, ROLE_ADMIN).and()
				.withUser("jiraya").password(DEFAULT_PASS).authorities(ROLE_NINJAS).and()
				.withUser("yusuke").password(DEFAULT_PASS).authorities(ROLE_USER).and()
				.withUser("fmoriguchi").password(DEFAULT_PASS).authorities(ROLE_ADMIN, ROLE_USER, ROLE_SAYAJINS, ROLE_NINJAS).and()
				.withUser("admin").password(DEFAULT_PASS).authorities(ROLE_ADMIN);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		
		super.configure(http);
	}
}
