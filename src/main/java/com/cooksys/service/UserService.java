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
	
	public User save(String firstName, String lastName, String username, String password) {
		
//		//Checks if a user is created
//		if (exists(user.getCredentials().getUsername())) {
//			//Gets the user if the user was created
//			TweetUser tUser = checkUserCredentials(user.getCredentials());
//			
//			//If the user was deleted will reactive the user
//			if (tUser != null && tUser.getIsActive().equals(false)) {
//				tUser.setIsActive(true);
//				return userRepository.save(tUser);
//			}
//			
//			throw new UsernameExistsException();
//		} 
		User user = new User();
		user.setPassword(username);
		user.setUsername(password);
		user.setFirstname(firstName);
		user.setLastname(lastName);
//		user.getProfile().setPhone(phone);
		
		//If all else fails then it creates a new user
		return repo.save(user);
	}

}
