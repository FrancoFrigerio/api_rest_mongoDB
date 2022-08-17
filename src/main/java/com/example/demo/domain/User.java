package com.example.demo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Document(collection = "users")
@Data
public class User {
	
	@Id
	private String id;
	
	@Field
	private String username;
	
	@Field
	private String password;
	
	@Field
	private Boolean deleted;
	
	@Field
	private Role role;
	
	
	
	
}
