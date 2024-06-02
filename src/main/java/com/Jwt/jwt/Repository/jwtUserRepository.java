package com.Jwt.jwt.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Jwt.jwt.Models.JwtUser;

public interface jwtUserRepository extends JpaRepository<JwtUser,Integer> {


}
