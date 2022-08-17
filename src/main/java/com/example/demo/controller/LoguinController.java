package com.example.demo.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.User;

import com.example.demo.service.UserService;




import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
@Controller
@RequestMapping("/loguin")
public class LoguinController {
	
	@Autowired
	UserService userService;
	
	private final String SECRET_KEY="secret";
	 
	public Algorithm getAlgorithm(){
	        return Algorithm.HMAC256(SECRET_KEY.getBytes());
	 }
	
	@GetMapping("/")
	public ResponseEntity<?>loguin(@RequestBody UserRequestDto userRequestDto){
		User user = userService.getByEmail(userRequestDto.getUsername());
			if(userRequestDto.getPassword().isEmpty() || !userRequestDto.getPassword().equalsIgnoreCase(user.getPassword())) {
			return new ResponseEntity<>("Please try again, the username or password is incorrect", HttpStatus.BAD_REQUEST);
		}else {
			Map<String, String>response = new HashMap<>();
			String[] roles = {"USER","ADMIN"};
			Algorithm algorithm = getAlgorithm();
			String accesToken = JWT.create()
					.withSubject(user.getUsername())
					.withExpiresAt(new Date(System.currentTimeMillis()+20*8*2000))
					.withArrayClaim("ROLES", roles)
					.sign(algorithm);
			response.put("accesToken", accesToken);
			response.put("message", "loguin success");
			return ResponseEntity.ok().body(response);
		}
		
	}
}
