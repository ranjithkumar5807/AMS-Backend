package ams.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ams.DTO.UserDTO;
import ams.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<UserDTO> findByEmail(String email);
}
