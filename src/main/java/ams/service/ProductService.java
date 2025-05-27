package ams.service;

import java.util.List;

import ams.model.Product;

public interface ProductService {
	public Product addProduct(Product product);
	public List<Product> getAllProducts();
	public List<Product> getProductsByFarmer(Long farmerId);
	public void deleteProduct(Long id);
	public Product getProductById(Long id);

}
