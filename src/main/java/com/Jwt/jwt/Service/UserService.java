package com.Jwt.jwt.Service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Jwt.jwt.Models.JwtUser;
import com.Jwt.jwt.Repository.jwtUserRepository;

@Service
public class UserService {
	@Autowired
	jwtUserRepository jwtUserrepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<JwtUser> getUsers() {
		List<JwtUser> users = jwtUserrepo.findAll();
		return users;
	}

	public String createJwtUser(JwtUser jwtUser) {
		String Message = "";

		JwtUser user = new JwtUser();
		try {
			user.setEmail(jwtUser.getEmail());
			user.setName(jwtUser.getName());
			user.setAbout(jwtUser.getAbout());
			user.setPassword(passwordEncoder.encode(jwtUser.getPassword()));
			jwtUserrepo.save(user);
			Message = "Data SAved Successfully";
			return Message;
		} catch (Exception e) {
			e.printStackTrace();
			Message = "DAta Not SAved";
		}
		return Message;
	}

	public String deleteUser(Integer id) {
		String message="";
		try {
			jwtUserrepo.deleteById(id);
			message="User Deleted Successfylly By Ritik Bia Pagala";

		} catch (Exception e) {
			e.printStackTrace();

		}
		return message;
	}

}
