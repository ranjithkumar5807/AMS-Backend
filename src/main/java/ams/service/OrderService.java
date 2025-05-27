package ams.service;

import java.util.List;

import ams.model.Order;
import ams.model.OrderItem;
import ams.model.User;

public interface OrderService {
	public Order placeOrder(User user);
	public List<Order> getUserOrders(Long userId);
	public List<OrderItem> getOrderItems(Long orderId);

}
