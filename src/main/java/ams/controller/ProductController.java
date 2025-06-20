package ams.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ams.model.Product;
//import ams.model.Product;
import ams.service.ProductService;
import ams.view.ProductView;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
    private ProductService productService;
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductView> getProductById(@PathVariable Long id){
		return productService.getProductByIdView(id).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
		
	}
	
	@PutMapping("/{productId}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long productId,@RequestBody Product product){
		return ResponseEntity.ok(productService.updateProduct(productId,product));
	}

    @GetMapping
    public ResponseEntity<List<ProductView>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @DeleteMapping("/{pid}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long pid) {
        productService.deleteProduct(pid);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/farmer/{id}")
    public ResponseEntity<List<ProductView>> getProductsByFarmerId(@PathVariable Long id){
    	return ResponseEntity.ok(productService.getProductsByFarmerId(id));
    }
}
