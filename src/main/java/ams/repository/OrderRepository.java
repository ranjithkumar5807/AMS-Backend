package ams.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ams.DTO.OrderDTO;
import ams.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByUserId(Long userId);

	@Query("SELECT new com.example.dto.OrderDTO(o.id, o.orderDate, o.totalAmount, null) " +
			"FROM Order o WHERE o.user.id = :userId")
    List<OrderDTO> findOrdersByUserId(@Param("userId") Long userId);

}
