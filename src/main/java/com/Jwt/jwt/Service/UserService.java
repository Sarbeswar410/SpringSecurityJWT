package com.Jwt.jwt.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Jwt.jwt.Models.JwtUser;
import com.Jwt.jwt.Repository.jwtUserRepository;

@Service
public class UserService {
	@Autowired
	jwtUserRepository jwtUserrepo;
	public List<JwtUser> getUsers() {
		List<JwtUser> users = jwtUserrepo.findAll();
		return users;
	}

	public String createJwtUser(JwtUser jwtUser) {
		String Message = "";

		JwtUser user = new JwtUser();
		try {
			user.setEmail(jwtUser.getEmail());
			user.setRole(jwtUser.getRole());
			user.setUserName(jwtUser.getUserName());
			user.setPassword(jwtUser.getPassword());
			jwtUserrepo.save(user);
			Message = "Data SAved Successfully";
			return Message;
		} catch (Exception e) {
			e.printStackTrace();
			Message = "DAta Not SAved";
		}
		return Message;
	}

}
