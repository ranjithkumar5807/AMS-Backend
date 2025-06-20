package ams.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ams.model.User;
import ams.view.UserView;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("select u from User u where u.id=?1")
	Optional<UserView> findByIdView(Long id);

	User findByEmail(String email);
	

}
