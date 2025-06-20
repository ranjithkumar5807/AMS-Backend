package ams.controller;

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
import ams.view.FarmerView;

@RestController
@RequestMapping("/api/farmers")
public class FarmerController {
	@Autowired
	private FarmerService farmerService;
	
	@PostMapping("/register")
    public ResponseEntity<Farmer> register(@RequestBody Farmer farmer) {
        return ResponseEntity.ok(farmerService.registerFarmer(farmer));
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<FarmerView> getViewById(@PathVariable Long id){
		return farmerService.getFarmerByIdView(id).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/{fid}/products")
	public ResponseEntity<Product> registerProduct(@PathVariable Long fid,@RequestBody Product product){
		return ResponseEntity.ok(farmerService.registerProductWithFarmerId(fid,product));
	}
	

}
