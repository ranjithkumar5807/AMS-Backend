package ams.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ams.DTO.UserDTO;
import ams.model.User;
//import ams.repository.CartRepository;
import ams.repository.UserRepository;
@Service
public class UserServiceImpl {
	
	@Autowired
	private UserRepository userRepository;


	
	public User registerUser(User user) {
		
		return userRepository.save(user);
	}

	
	public Optional<UserDTO> getUserByEmail(String email) {
		
		return userRepository.findByEmail(email);
	}


	public Optional<User> getUserById(Long Id) {
		return userRepository.findById(Id);
	}

}
