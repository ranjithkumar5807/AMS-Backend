package ams.serviceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ams.model.Farmer;
import ams.model.Product;
import ams.repository.FarmerRepository;
import ams.service.FarmerService;
@Service
public class FarmerServiceImpl implements FarmerService {
	
	@Autowired
	private FarmerRepository farmerRepository;

	@Override
	public Farmer registerFarmer(Farmer farmer) {
		
		return farmerRepository.save(farmer);
	}

	@Override
	public List<Product> getFarmerProducts(Long farmerId) {
		return farmerRepository.findById(farmerId)
				.map(Farmer::getProducts)
				.orElse(Collections.emptyList());
		
	}

	@Override
	public Optional<Farmer> getFarmerByEmail(String email) {
		
		return farmerRepository.findByEmail(email);
	}

}
