package ams.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ams.model.OrderItem;
import ams.view.OrderItemView;
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	
	
	@Query("select oi.id as id,oi.product.id as productId,oi.product.name as productName,"+
	"oi.quantity as quantity,oi.price as price "+
	"FROM OrderItem as oi where oi.order.id=?1")
	List<OrderItemView> findByOrderIdView(Long orderId);
	
}
