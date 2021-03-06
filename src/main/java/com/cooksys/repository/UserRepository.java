package com.cooksys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findById(Integer id);
	
	User findByUsername(String username);
	
	User findByUsernameEqualsAndPasswordEquals(String username, String password);

}
