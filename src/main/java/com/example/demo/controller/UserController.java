package com.example.demo.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;



@Controller
@RequestMapping("/users")
public class UserController {

	
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/")
	private ResponseEntity<?>getAllUsers(){
		return ResponseEntity.ok(userService.getAll());
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<?>getById(@PathVariable(required = true) String id){
		return ResponseEntity.ok(userService.getById(id));
	}
	
	@GetMapping("/query")
	private ResponseEntity<?>getByIdQueryParam(@RequestParam(name = "id") String id){
	return ResponseEntity.ok(userService.getById(id));
	}
	
	@GetMapping("/lastName")
	private ResponseEntity<?>getByLastName(@RequestParam(name = "username") String username){
	return ResponseEntity.ok(userService.getByUsername(username));
	}
	
	
	@GetMapping("/roleDescription")
	private ResponseEntity<?>filterByDescription(@RequestParam(name = "roleDescription") String roleDescription){
	return ResponseEntity.ok(userService.filterByRoleDescription(roleDescription));
	}
	
	@PostMapping("/")
	private ResponseEntity<?>saveUser(@RequestBody User user){
		System.out.println(user.toString());
		userService.saveUser(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
