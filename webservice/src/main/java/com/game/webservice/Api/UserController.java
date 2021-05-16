package com.game.webservice.Api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.game.webservice.Error.*;
import com.game.webservice.Service.*;
import com.game.webservice.Shared.*;
import com.game.webservice.Entities.*;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping("/api/1.0/users")
	public GenericResponse createUser(@Valid @RequestBody Users user) {
		
		/*ApiError error = new ApiError(400, "Validation error", "/api/1.0/users");
		Map<String, String> validationErrors = new HashMap<>();
		
		
		String username = user.getUsername();
		String displayName = user.getDisplayName();
		
		if(username == null || username.isEmpty()) {
			validationErrors.put("username", "Username cannot be null");
		}

		if(displayName == null || displayName.isEmpty()) {
			validationErrors.put("displayName", "displayName cannot be null");
		}
		if(validationErrors.size()>0) {
			error.setValidationErrors(validationErrors);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		}*/
		userService.save(user);
		return new GenericResponse("User Created!");
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError handleValidationException(MethodArgumentNotValidException exception) {
		ApiError error = new ApiError(400, "Validation error", "/api/1.0/users");
		Map<String, String> validationErrors = new HashMap<>();
		for(FieldError fieldError: exception.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		error.setValidationErrors(validationErrors);
		return error;
	}
}
