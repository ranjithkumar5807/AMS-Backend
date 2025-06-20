package ams.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ams.model.CartItem;
import ams.model.Order;
import ams.model.OrderItem;
import ams.model.Product;
import ams.model.User;
import ams.repository.CartItemRepository;
import ams.repository.OrderItemRepository;
import ams.repository.OrderRepository;
import ams.repository.ProductRepository;
import ams.view.OrderItemView;
import ams.view.OrderView;

@Service
public class OrderService {
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CartItemRepository cartItemRepository;
	@Autowired
	private ProductRepository productRepository;
	
	
public Order placeOrder(User user) {
		
		List<CartItem> cartItems=cartItemRepository.findByUserId(user.getId());
		if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty. Cannot place order.");
        }
		
		Order order = new Order();
		order.setStatus("PENDING");
		order.setUser(user);
		order.setOrderDate(LocalDate.now());

		double total = 0;
		List<OrderItem> orderItems = new ArrayList<>();

		for (CartItem item : cartItems) {
			Product product = item.getProduct();

			if (product.getQuantity() < item.getQuantity()) {
				throw new RuntimeException("Not enough stock for product: " + product.getName());
			}

			// Update stock
			product.setQuantity(product.getQuantity() - item.getQuantity());
			productRepository.save(product);

			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(order);
			orderItem.setProduct(product);
			orderItem.setQuantity(item.getQuantity());
			orderItem.setPrice(product.getPrice()*item.getQuantity());

			orderItems.add(orderItem);
			total += orderItem.getPrice();
		}

		order.setTotalPrice(total);
		order.setOrderItems(orderItems);

		Order savedOrder = orderRepository.save(order);
		orderItemRepository.saveAll(orderItems);
		cartItemRepository.deleteAll(cartItems);

		return savedOrder;
}

	
//	public List<OrderView> getUserOrders(Long userId) {
//		return orderRepository.findByUserIdView(userId);
//		
//	}

	
	public List<OrderItemView> getOrderItems(Long orderId) {
		return orderItemRepository.findByOrderIdView(orderId);
	}

	public List<OrderView> getOrdersByUserId(Long userId) {
        List<OrderView> orders = orderRepository.findOrdersByUserIdView(userId);

        for (OrderView order : orders) {
            List<OrderItemView> items = orderItemRepository.findByOrderIdView(order.getId());
            order.setOrderItems(items);
        }

        return orders;
    }

}


