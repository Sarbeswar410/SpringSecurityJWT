package com.Jwt.jwt.Models;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class JwtUser {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer Id;
	private String userName;
	private String password;
	private String role;
	private String email;
	

}
