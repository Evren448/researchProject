package com.game.webservice.Validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.game.webservice.Annotations.*;
import com.game.webservice.Service.*;
import com.game.webservice.Entities.*;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String>{
	@Autowired
	IUserRepository userRepository;
	
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		Users user = userRepository.findByEmail(email);
		if(user!= null) {
			return false;
		}
		return true;
	}
	

}