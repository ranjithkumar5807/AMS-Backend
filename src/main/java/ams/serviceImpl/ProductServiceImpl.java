package ams.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ams.model.Product;
import ams.repository.ProductRepository;
import ams.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public List<Product> getProductsByFarmer(Long farmerId) {
		return productRepository.findByFarmerId(farmerId);
	}

	@Override
	public void deleteProduct(Long id) {
		Optional<Product> opproduct=productRepository.findById(id);
		if(opproduct.isPresent()) {
			productRepository.delete(opproduct.get());
		}
		

	}

}
