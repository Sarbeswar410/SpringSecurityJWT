package com.Jwt.jwt.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Jwt.jwt.Models.JwtUser;
import com.Jwt.jwt.Service.UserService;

@RestController
@RequestMapping("/api")

public class HomeController {
	@Autowired
	UserService userService;
	@GetMapping("/currentUser")
	public String getLoggedInUser(Principal principal) {
		return principal.getName();
		
	}
	@GetMapping("/users")
		public List<JwtUser> getUser(){
			return userService.getUsers();
		}
	}


