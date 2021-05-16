package com.game.webservice.Service;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.game.webservice.Entities.*;



@Service
public class UserService {
EmailService emailService;
	
	IUserRepository userRepository;
	
	PasswordEncoder passwordEncoder;
	
	public UserService(IUserRepository userRepository, EmailService emailService) {
		this.userRepository = userRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
		this.emailService = emailService;
	}

	public void save(Users user) {
		String encryptedPassword = this.passwordEncoder.encode(user.getPassword());
		user.setPassword(encryptedPassword);
		userRepository.save(user);
		String information = "Dear " + user.getUsername() + " congratulations. Your registration has been successfully completed.";
		emailService.sendEmail(user.getEmail(), information, "About Registration");
	}	
}