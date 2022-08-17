package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.service.dto.UserResponseDto;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public User getById(String id) {
		return userRepository.findById(id).get();
	}

	@Override
	public List<UserResponseDto> getAll() {
		List<UserResponseDto> resp = new ArrayList<>();
		userRepository.findAll().forEach(user ->{
			resp.add(mapper.map(user, UserResponseDto.class));
			
		});;
		return resp;
	}

	@Override
	public void saveUser(User user) {
		userRepository.save(user);
		
	}

	@Override
	public UserResponseDto getByUsername(String username) {
		return mapper.map(userRepository.getByUsername(username), UserResponseDto.class);
	}

	@Override
	public List<User> filterByRoleDescription(String roleDescription) {
		return userRepository.filterByDescription(roleDescription);
	}

	@Override
	public User getByEmail(String email) {
		
		return userRepository.getByUsername(email);
	}

}
