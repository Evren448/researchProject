package com.game.webservice.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

import com.game.webservice.Annotations.UniqueEmail;
import com.game.webservice.Annotations.UniqueUsername;

import lombok.Data;

@Data
@Entity
@Table(name="users",catalog = "game")
public class Users {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	
	@NotNull
	@Size(min = 4, max = 255)
	@UniqueUsername
	@Column(name="username")
	private String username;
	
	@NotNull
	@Size(min = 4, max = 255)
	@Column(name="email")
	@Email
	@UniqueEmail
	private String email;
	
	@NotNull
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$")
	@Size(min = 8, max = 255)
	@Column(name="password")
	private String password;
	

}
