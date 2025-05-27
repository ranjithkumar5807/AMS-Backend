package ams.serviceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ams.DTO.FarmerDTO;
import ams.model.Farmer;
import ams.model.Product;
import ams.repository.FarmerRepository;
@Service
public class FarmerServiceImpl {
	
	@Autowired
	private FarmerRepository farmerRepository;

	
	public Farmer registerFarmer(Farmer farmer) {
		
		return farmerRepository.save(farmer);
	}

	
	public List<Product> getFarmerProducts(Long farmerId) {
		return farmerRepository.findById(farmerId)
				.map(Farmer::getProducts)
				.orElse(Collections.emptyList());
		
	}

	
	public Optional<FarmerDTO> getFarmerByEmail(String email) {
		
		return farmerRepository.findByEmail(email);
	}

}
