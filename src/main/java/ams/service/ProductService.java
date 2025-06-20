package ams.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ams.model.Product;
import ams.repository.ProductRepository;
import ams.view.ProductView;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	public Product register(Product product) {
		return productRepository.save(product);
	}
	public Optional<ProductView> getProductByIdView(Long id) {
		return productRepository.findByIdView(id);
	}
	public Product getProductById(Long id) {
		return productRepository.findById(id).orElse(null);
	}
	public List<ProductView> getAllProducts(){
		return productRepository.findAllView();
	}
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
	public List<ProductView> getProductsByFarmerId(Long fid){
		return productRepository.findProductsByFarmerId(fid);	
	}
	public Product updateProduct(Long productId,Product product) {
		Product p=productRepository.findById(productId).orElse(null);
		if (p==null) {
			return null;
		}
		product.setId(productId);
		return productRepository.save(product);	
	}
}
