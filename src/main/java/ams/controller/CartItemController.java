package ams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ams.model.Product;
import ams.model.User;
import ams.service.CartItemService;
import ams.service.ProductService;
import ams.service.UserService;
import ams.view.CartItemView;


@RestController
@RequestMapping("/api/cart")
public class CartItemController {
	@Autowired
    private CartItemService cartService;
	@Autowired
    private  UserService userService;
	@Autowired
    private ProductService productService;
	
	@PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestParam Long userId,
                                            @RequestParam Long productId,
                                            @RequestParam int quantity) {
        User user = userService.getUserById(userId);
        Product product = productService.getProductById(productId);
        cartService.addToCart(user, product, quantity);
        return ResponseEntity.ok("Item added to cart");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItemView>> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartItems(userId));
    }
	

}
