package ams.service;

import java.util.List;
import java.util.Optional;

import ams.model.Farmer;
import ams.model.Product;

public interface FarmerService {
	public Farmer registerFarmer(Farmer farmer);
	public List<Product> getFarmerProducts(Long farmerId);
	public Optional<Farmer> getFarmerByEmail(String email);

}
