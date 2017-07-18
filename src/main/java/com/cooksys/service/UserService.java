package com.cooksys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooksys.entity.Credentials;
import com.cooksys.entity.User;
import com.cooksys.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository repo;
		
	public List<User> getAll()
	{
		return repo.findAll();
	}

	public User get(Integer id) {
		return repo.findById(id);
	}
	
	public User get(String name) {
		return repo.findByUsername(name);
	}
	
	public User create(User user) {
		return repo.save(user);
	}
	
	public User checkUserCredentials(Credentials creds) {
		return repo
				.findByUsernameEqualsAndPasswordEquals(
						creds.getUsername(), creds.getPassword());
	}

}
