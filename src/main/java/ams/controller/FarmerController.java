package ams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ams.model.Farmer;
import ams.model.Product;
import ams.service.FarmerService;
import ams.service.ProductService;

@RestController
@RequestMapping("/api/farmers")
public class FarmerController {

	@Autowired
    private  FarmerService farmerService;
	@Autowired
    private  ProductService productService;

    @PostMapping("/register")
    public ResponseEntity<Farmer> register(@RequestBody Farmer farmer) {
        return ResponseEntity.ok(farmerService.registerFarmer(farmer));
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<List<Product>> getProducts(@PathVariable Long id) {
        return ResponseEntity.ok(farmerService.getFarmerProducts(id));
    }

    @PostMapping("/{id}/products")
    public ResponseEntity<Product> addProduct(@PathVariable Long id, @RequestBody Product product) {
        Farmer farmer = new Farmer();
        farmer.setId(id);
        product.setFarmer(farmer);
        return ResponseEntity.ok(productService.addProduct(product));
    }
}