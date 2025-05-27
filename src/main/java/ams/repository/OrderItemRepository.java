package ams.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ams.DTO.OrderItemDTO;
import ams.model.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	List<OrderItem> findByOrderId(Long orderId);

		@Query("SELECT new com.example.dto.OrderItemDTO(oi.id, p.id, p.name, oi.quantity, oi.price) " +
			"FROM OrderItem oi JOIN oi.product p WHERE oi.order.id = :orderId")
    List<OrderItemDTO> findOrderItemsByOrderId(@Param("orderId") Long orderId);

}
