package com.restapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringConfig {
	
	
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		
//		UserDetails userDetails1=User.withUsername("user1").password(passwordEncoder().encode("pass1")).roles("USER").build();
//		UserDetails userDetails2=User.withUsername("user2").password(passwordEncoder().encode("pass2")).roles("USER").build();
//		UserDetails userDetails3=User.withUsername("admin").password(passwordEncoder().encode("admin1")).roles("ADMIN").build();
//		return new InMemoryUserDetailsManager(userDetails1, userDetails2, userDetails3);
//		
//		
//	}

	
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() ;  // ✅ disable CSRF for REST APIs
        http.authorizeHttpRequests(request -> request.requestMatchers("/hello", "/user-info/register").permitAll()
        		.anyRequest().authenticated());
        http.httpBasic(Customizer.withDefaults());
        http.sessionManagement(session -> session.
        		sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
        
      
    }
	
	
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		
		return daoAuthenticationProvider;
	}
}
