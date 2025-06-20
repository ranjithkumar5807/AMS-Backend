package ams.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ams.model.Farmer;
import ams.model.User;
import ams.repository.FarmerRepository;
import ams.repository.UserRepository;

@Service
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FarmerRepository farmerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userInfo = userRepository.findByEmail(username);
        if (userInfo!=null) {
            return new UserInfoUserDetails(userInfo);
        }

        Optional<Farmer> farmer = farmerRepository.findByEmail(username);
        if (farmer.isPresent()) {
//        	System.out.println(farmer);
            return new FarmerUserDetails(farmer.get());
        }
//        System.out.println("Nothing");

        throw new UsernameNotFoundException("User not found with email: " + username);
    }
}
