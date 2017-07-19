package com.cooksys.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.entity.Credentials;
import com.cooksys.entity.User;
import com.cooksys.service.UserService;

@RestController
@RequestMapping("users")
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping
	public List<User> get() {
		return userService.getAll();
	}

	@RequestMapping("/{id}")
	public User get(@PathVariable("id") Integer id) {
		return userService.get(id);
	}
	
	@RequestMapping("/name")
	public User get(@RequestParam("name") String username)
	{
		return userService.get(username);
	}
		
//	@RequestMapping(method = RequestMethod.POST)
//	public User create(@RequestBody User user, HttpServletResponse response) {
//		System.out.println(user.getFirstname());
//		System.out.println(user.getLastname());
//		return userService.create(user);
//	}
	
	@RequestMapping(method = RequestMethod.POST)
	public User create(
			@RequestParam(required = false) String firstname, 
			@RequestParam(required = false) String lastname,
			@RequestParam(required = false) String username,
			@RequestParam(required = false) String password,
			HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_CREATED);
		return userService.save(firstname, lastname, username, password);
		
	}
	
	@RequestMapping(value = "users/validate/user", method = RequestMethod.POST)
	public User validateAUser(@RequestBody Credentials creds, HttpServletResponse response) {
		return userService.checkUserCredentials(creds);
	}

}
