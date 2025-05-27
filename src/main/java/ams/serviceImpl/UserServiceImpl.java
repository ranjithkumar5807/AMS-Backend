package ams.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ams.model.Cart;
import ams.model.User;
//import ams.repository.CartRepository;
import ams.repository.UserRepository;
import ams.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userrepository;
//	@Autowired
//	private CartRepository cartrepository;

	@Override
	public User registerUser(User user) {
		
		Cart cart= new Cart();
		cart.setUser(user);
		user.setCart(cart);
		return userrepository.save(user);
	}

	@Override
	public Optional<User> getUserByEmail(String email) {
		
		return userrepository.findByEmail(email);
	}

	@Override
	public Optional<User> getUserById(Long Id) {
		return userrepository.findById(Id);
	}

}
