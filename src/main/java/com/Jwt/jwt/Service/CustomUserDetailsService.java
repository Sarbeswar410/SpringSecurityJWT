package com.Jwt.jwt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Jwt.jwt.Models.JwtUser;
import com.Jwt.jwt.Repository.jwtUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private jwtUserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		JwtUser user = userRepo.findByEmail(username).orElseThrow(() -> new RuntimeException("user Not Found"));
		return user;
	}

}
