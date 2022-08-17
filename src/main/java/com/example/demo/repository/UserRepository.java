package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{
	
	@Query("{username: '?0'}")
	public User getByUsername(String username);
	
	
	@Query(value="{'role.description': '?0'}", fields="{'username':1}")
	public List<User>filterByDescription(String definitionRole);
}
