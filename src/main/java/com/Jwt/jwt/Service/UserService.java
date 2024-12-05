package com.Jwt.jwt.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Jwt.jwt.Models.JwtUser;
import com.Jwt.jwt.Repository.jwtUserRepository;
import com.Jwt.jwt.Utility.ServiceResponse;

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

	public ServiceResponse createJwtUser(JwtUser jwtUser) {
		ServiceResponse response = new ServiceResponse();
		JwtUser user = new JwtUser();
		Optional<JwtUser> email = jwtUserrepo.findByEmail(jwtUser.getEmail());
		if(email.isPresent()) {
			response.setServiceResponse("Exist");
			response.setServiceStatus(response.STATUS_FAIL);
			return response;
		}
		try {
			user.setEmail(jwtUser.getEmail());
			user.setName(jwtUser.getName());
			user.setAbout(jwtUser.getAbout());
			user.setPassword(passwordEncoder.encode(jwtUser.getPassword()));
			jwtUserrepo.save(user);
			response.setServiceStatus(response.STATUS_SUCCESS);
		} catch (Exception e) {
			response.setServiceStatus(response.STATUS_FAIL);
		}
		return response;
	}

	public String deleteUser(Integer id) {
		String message = "";
		if (jwtUserrepo.existsById(id)) {
			try {
				jwtUserrepo.deleteById(id);
				message = "User deleted successfully by Ritik Bia Pagala";
			} catch (Exception e) {
				e.printStackTrace();
				message = "An error occurred while deleting the user.";
			}
		} else {
			message = "User with ID " + id + " not found.";
		}
		return message;
	}

}
