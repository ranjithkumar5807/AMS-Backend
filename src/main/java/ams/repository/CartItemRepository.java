package ams.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ams.DTO.CartItemDTO;
import ams.model.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	List<CartItem> findByCartId(Long cartId);

	@Query("SELECT new com.example.dto.CartItemDTO(ci.id, p.id, p.name, p.price, ci.quantity) " +
			"FROM CartItem ci JOIN ci.product p WHERE ci.user.id = :userId")
    List<CartItemDTO> findCartItemsByUserId(@Param("userId") Long userId);
}
