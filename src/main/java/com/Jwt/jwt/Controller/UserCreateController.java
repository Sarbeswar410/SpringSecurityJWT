package com.Jwt.jwt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Jwt.jwt.Models.JwtUser;
import com.Jwt.jwt.Service.UserService;

@RequestMapping("/user")
@RestController
public class UserCreateController {
	@Autowired
	UserService userService;
	@PostMapping("/createUser")
	public String createUser(@RequestBody JwtUser jwtUser) {
		String message=userService.createJwtUser(jwtUser);
		return message;
		
	}

}
