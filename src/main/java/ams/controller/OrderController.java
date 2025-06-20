package ams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ams.model.Order;
import ams.model.User;
import ams.service.OrderService;
import ams.service.UserService;
import ams.view.OrderItemView;
import ams.view.OrderView;

@Controller
@RequestMapping("/api/orders")
public class OrderController {
	@Autowired
    private OrderService orderService;
	@Autowired
    private UserService userService;
	
	@PostMapping("/place")
    public ResponseEntity<Order> placeOrder(@RequestParam Long userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(orderService.placeOrder(user));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<OrderView>> getUserOrders(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }

    @GetMapping("/{orderId}/items")
    public ResponseEntity<List<OrderItemView>> getOrderItems(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrderItems(orderId));
    }

}
