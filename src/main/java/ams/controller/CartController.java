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

import ams.model.CartItem;
import ams.model.Product;
import ams.model.User;
import ams.service.CartService;
import ams.service.ProductService;
import ams.service.UserService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	@Autowired
    private CartService cartService;
	@Autowired
    private  UserService userService;
	@Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestParam Long userId,
                                            @RequestParam Long productId,
                                            @RequestParam int quantity) {
        User user = userService.getUserById(userId).orElseThrow();
        Product product = productService.getProductsByFarmer(productId).stream().findFirst().orElseThrow();
        cartService.addToCart(user, product, quantity);
        return ResponseEntity.ok("Item added to cart");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItem>> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartItems(userId));
    }
}