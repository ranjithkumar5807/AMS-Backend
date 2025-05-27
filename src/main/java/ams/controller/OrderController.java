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

import ams.model.Order;
import ams.model.OrderItem;
import ams.model.User;
import ams.service.OrderService;
import ams.service.UserService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	@Autowired
    private OrderService orderService;
	@Autowired
    private UserService userService;

    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(@RequestParam Long userId) {
        User user = userService.getUserById(userId).orElseThrow();
        return ResponseEntity.ok(orderService.placeOrder(user));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Order>> getUserOrders(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getUserOrders(userId));
    }

    @GetMapping("/{orderId}/items")
    public ResponseEntity<List<OrderItem>> getOrderItems(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrderItems(orderId));
    }
}