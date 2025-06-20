package ams.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ams.model.Order;
import ams.view.OrderView;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	

	@Query("SELECT new ams.view.OrderView(o.id, o.orderDate, o.totalPrice) " +
		       "FROM Order o WHERE o.user.id = ?1")
		List<OrderView> findOrdersByUserIdView(Long userId);

}
