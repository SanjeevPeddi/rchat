package com.rsrit.rchat.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private JwtTokenStore tokenStore;

	public ResourceServerConfig(JwtTokenStore tokenStore) {
		this.tokenStore = tokenStore;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/stomp/**")
		.permitAll()
		.antMatchers("/rchat/api/user/**")
		.permitAll()
		.antMatchers("/ws/**")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.csrf().ignoringAntMatchers("/ws/**");
		super.configure(http);
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
		super.configure(resources);
	}

}
