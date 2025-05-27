package ams.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ams.DTO.FarmerDTO;
import ams.model.Farmer;

@Repository
public interface FarmerRepository  extends JpaRepository<Farmer, Long>{

	Optional<FarmerDTO> findByEmail(String email);

}
