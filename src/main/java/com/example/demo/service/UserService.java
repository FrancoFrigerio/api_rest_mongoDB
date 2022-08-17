package com.example.demo.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.service.dto.UserResponseDto;

@Service
public interface UserService {

	public User getById(String id);
	
	public User getByEmail(String email);
	
	public UserResponseDto getByUsername(String username);
	
	public List<UserResponseDto> getAll();
	
	public List<User>filterByRoleDescription(String roleDescription);
	
	void saveUser(User user);
	
}
