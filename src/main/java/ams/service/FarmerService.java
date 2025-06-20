package ams.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ams.model.Farmer;
import ams.model.Product;
import ams.repository.FarmerRepository;
import ams.repository.ProductRepository;
import ams.view.FarmerView;
import jakarta.transaction.Transactional;

@Service
public class FarmerService {
	@Autowired
	private FarmerRepository farmerRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public Farmer registerFarmer(Farmer farmer) {
		farmer.setPassword(passwordEncoder.encode(farmer.getPassword()));
		return farmerRepository.save(farmer);
	}
	
	public Optional<FarmerView> getFarmerByIdView(Long id){
		return farmerRepository.findByIdView(id);
	}
	public Product registerProductWithFarmerId(Long fid,Product product) {
		Farmer farmer=farmerRepository.findById(fid).orElse(null);
		if(farmer!=null) {
			product.setFarmer(farmer);
			return productRepository.save(product);
		}else {
			return null;
		}
	}

}
