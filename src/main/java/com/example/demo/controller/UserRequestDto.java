package com.example.demo.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequestDto {
	private String username;
	private String password;
}
