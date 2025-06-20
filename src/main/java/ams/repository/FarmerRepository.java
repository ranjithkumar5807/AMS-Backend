package ams.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ams.model.Farmer;
import ams.view.FarmerView;
@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Long> {
	
	@Query("select f from Farmer f where f.id=?1")
	Optional<FarmerView> findByIdView(Long id);
	@Query("select f from Farmer f where f.email=?1")
	Farmer getByEmail(String email);
	Optional<Farmer> findByEmail(String username);

}
