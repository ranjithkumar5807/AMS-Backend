package ams.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ams.model.Cart;
import ams.model.CartItem;
import ams.model.Order;
import ams.model.OrderItem;
import ams.model.User;
import ams.repository.CartItemRepository;
import ams.repository.CartRepository;
import ams.repository.OrderItemRepository;
import ams.repository.OrderRepository;
import ams.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private CartItemRepository cartItemRepository;

	@Override
	public Order placeOrder(User user) {
		Cart cart=user.getCart();
		List<CartItem> cartItems=cart.getCartitems();
		
		Order order=new Order();
		order.setStatus("PENDING");
		order.setUser(user);
		order.setOrderDate(LocalDate.now());
		
		double total=0;
		List<OrderItem> orderItems= new ArrayList<>();
		for(CartItem item: cartItems) {
			OrderItem orderItem=new OrderItem();
			orderItem.setOrder(order);
			orderItem.setProduct(item.getProduct());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setPrice(item.getPrice());
			orderItems.add(orderItem);
			total+=item.getPrice();
		}
		order.setTotalPrice(total);
		order.setOrderItems(orderItems);
		Order savedOrder =orderRepository.save(order);
		cartItemRepository.deleteAll(cartItems);
		
		return savedOrder;
		
	}

	@Override
	public List<Order> getUserOrders(Long userId) {
		return orderRepository.findByUserId(userId);
		
	}

	@Override
	public List<OrderItem> getOrderItems(Long orderId) {
		return orderItemRepository.findByOrderId(orderId);
	}

}
