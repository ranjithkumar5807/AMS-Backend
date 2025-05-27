package ams.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ams.DTO.OrderDTO;
import ams.DTO.OrderItemDTO;
import ams.model.CartItem;
import ams.model.Order;
import ams.model.OrderItem;
import ams.model.Product;
import ams.model.User;
import ams.repository.CartItemRepository;
import ams.repository.OrderItemRepository;
import ams.repository.OrderRepository;
import ams.repository.ProductRepository;
@Service
public class OrderServiceImpl {
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CartItemRepository cartItemRepository;
	@Autowired
	private ProductRepository productRepository;

	
	public Order placeOrder(User user) {
		
		List<CartItem> cartItems=cartItemRepository.findByCartId(user.getId());
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

	
	public List<Order> getUserOrders(Long userId) {
		return orderRepository.findByUserId(userId);
		
	}

	
	public List<OrderItem> getOrderItems(Long orderId) {
		return orderItemRepository.findByOrderId(orderId);
	}

	public List<OrderDTO> getOrdersByUserId(Long userId) {
        List<OrderDTO> orders = orderRepository.findOrdersByUserId(userId);

        for (OrderDTO order : orders) {
            List<OrderItemDTO> items = orderItemRepository.findOrderItemsByOrderId(order.getId());
            order.setOrderItems(items);
        }

        return orders;
    }

}
