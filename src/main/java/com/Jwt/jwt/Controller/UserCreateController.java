package com.Jwt.jwt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Jwt.jwt.Models.JwtUser;
import com.Jwt.jwt.Service.UserService;
import com.Jwt.jwt.Utility.ServiceResponse;

@RequestMapping("/user")
@RestController
@CrossOrigin(origins = "http://192.168.1.11:4200", allowedHeaders = "*")
public class UserCreateController {
	@Autowired
	UserService userService;
	@PostMapping("/createUser")
	public ServiceResponse createUser(@RequestBody JwtUser jwtUser) {
		ServiceResponse response=userService.createJwtUser(jwtUser);
		return response;
		
	}
	
	
	@DeleteMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable Integer id) {
		String message= userService.deleteUser(id);
		return message;
		
	}
		
	
	

}
