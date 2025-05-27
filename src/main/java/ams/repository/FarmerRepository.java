package ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ams.model.Farmer;

@Repository
public interface FarmerRepository  extends JpaRepository<Farmer, Long>{

}
