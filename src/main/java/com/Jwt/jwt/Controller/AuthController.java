package com.Jwt.jwt.Controller;

import org.hibernate.internal.build.AllowSysOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Jwt.jwt.Models.JwtRequest;
import com.Jwt.jwt.Models.JwtResponse;
import com.Jwt.jwt.Security.JwtHelperClass;
import com.Jwt.jwt.Service.UserService;
import com.Jwt.jwt.Utility.ServiceResponse;

@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins = "http://localhost:4200")

public class AuthController {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	UserService service;

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private JwtHelperClass helper;

	private Logger logger = LoggerFactory.getLogger(AuthController.class);

	@PostMapping("/login")
	public ServiceResponse login(@RequestBody JwtRequest request) {
		ServiceResponse response = new ServiceResponse();
		try {
			this.doAuthenticate(request.getEmail(), request.getPassword());

			UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
			String token = this.helper.generateToken(userDetails);
			logger.info(token);
			JwtResponse jwtResponse = JwtResponse.builder().jwtToken(token).username(userDetails.getUsername()).build();
			response.setServiceResponse(jwtResponse);
			response.setServiceStatus(response.STATUS_SUCCESS);

		} catch (Exception e) {
			response.setServiceStatus(response.STATUS_FAIL);
		}

		return response;
	}

	private void doAuthenticate(String userName, String password) {

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userName,
				password);
		try {
			manager.authenticate(authentication);

		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(" Invalid Username or Password  !!");
		}

	}

	@ExceptionHandler(BadCredentialsException.class)
	public String exceptionHandler() {
		return "Credentials Invalid !!!";

	}

}
