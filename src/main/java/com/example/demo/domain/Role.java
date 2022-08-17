package com.example.demo.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document("roles")
public class Role {
	
	@Field
	private String idRole;
	
	@Field
	private String description;
}
