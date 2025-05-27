package ams.service;

import java.util.Optional;

import ams.model.User;

public interface UserService {
	
	public User registerUser(User user);
	public Optional<User> getUserByEmail(String email);
	public Optional<User> getUserById(Long Id);
	

}
