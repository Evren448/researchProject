package com.game.webservice.Validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.game.webservice.Annotations.*;
import com.game.webservice.Service.*;
import com.game.webservice.Entities.*;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String>{

	@Autowired
	IUserRepository userRepository;
	
	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		
		Users user = userRepository.findByUsername(username);
		if(user!= null) {
			return false;
		}
		return true;
	}

}