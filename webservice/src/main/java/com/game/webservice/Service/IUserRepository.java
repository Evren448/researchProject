package com.game.webservice.Service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.game.webservice.Entities.*;

public interface IUserRepository extends JpaRepository<Users, Long>{
	
	Users findByUsername(String username);
	Users findByEmail(String email);
}