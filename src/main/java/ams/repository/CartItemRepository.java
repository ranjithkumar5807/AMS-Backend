package ams.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ams.model.CartItem;
import ams.view.CartItemView;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{
	
	@Query("select ci.id as id,ci.quantity as quantity,ci.product.id as productId ,ci.product.name as productName,"+
	"ci.product.price as productPrice from CartItem ci where ci.user.id=?1")
	List<CartItemView> findCartItemsByUserIdView(Long uid);
	
	@Query("select ci from CartItem ci where ci.user.id=?1 and ci.product.id=?2")
	CartItem findByUserIdAndProductId(Long uid,Long pid);
	
	@Query("select ci from CartItem ci where ci.user.id=?1")
	List<CartItem> findByUserId(Long uid);

}
