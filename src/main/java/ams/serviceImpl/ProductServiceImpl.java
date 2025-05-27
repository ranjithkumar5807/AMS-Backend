package ams.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ams.DTO.ProductDTO;
import ams.model.Product;
import ams.repository.ProductRepository;

@Service
public class ProductServiceImpl {
	
	@Autowired
	private ProductRepository productRepository;

	
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}


	public void deleteProduct(Long id) {
		Optional<Product> opproduct=productRepository.findById(id);
		if(opproduct.isPresent()) {
			productRepository.delete(opproduct.get());
		}
		

	}

	public List<ProductDTO> getAllProducts() {
        return productRepository.findAllProductDTOs();
    }

    public List<ProductDTO> getProductsByFarmerId(Long farmerId) {
        return productRepository.findProductsByFarmerId(farmerId);
    }

}
