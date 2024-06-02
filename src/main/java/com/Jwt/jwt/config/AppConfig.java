package com.Jwt.jwt.config;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.Jwt.jwt.Models.JwtUser;
import com.Jwt.jwt.Repository.jwtUserRepository;

@Configuration
public class AppConfig {
	@Autowired
	jwtUserRepository userRepository;
	@Bean
	public UserDetailsService userDetailsService() {
		  List<JwtUser> users = userRepository.findAll();
		  System.out.println(users);

		  List<UserDetails> userDetailsList = new ArrayList<>();
	        for (JwtUser user : users) {
	            UserDetails userDetails = User.builder()
	                    .username(user.getUserName())
	                    .password(passwordEncoder().encode(user.getPassword()))
	                    .roles(user.getRole())
	                    .build();
	            userDetailsList.add(userDetails);
	        }

	        return new InMemoryUserDetailsManager(userDetailsList);

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
		return builder.getAuthenticationManager();
	}

}
