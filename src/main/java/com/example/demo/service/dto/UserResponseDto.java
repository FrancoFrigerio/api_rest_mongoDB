package com.example.demo.service.dto;

import com.example.demo.domain.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
	private String id;
	private String username;
	private Role role;
}
