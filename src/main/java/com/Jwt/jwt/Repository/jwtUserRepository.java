package com.Jwt.jwt.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Jwt.jwt.Models.JwtUser;

public interface jwtUserRepository extends JpaRepository<JwtUser,Integer> {

  public Optional<JwtUser> findByEmail(String email);
}
