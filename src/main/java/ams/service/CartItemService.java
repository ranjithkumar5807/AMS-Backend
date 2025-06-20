package ams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ams.model.CartItem;
import ams.model.Product;
import ams.model.User;
//import ams.model.CartItem;
import ams.repository.CartItemRepository;
import ams.view.CartItemView;

@Service
public class CartItemService {
	@Autowired
	private CartItemRepository cartItemRepository ;
	
	 public List<CartItemView> getCartItems(Long uid){
		 return cartItemRepository.findCartItemsByUserIdView(uid);
		
	}

	public void addToCart(User user, Product product, int quantity) {
		
		CartItem avail=cartItemRepository.findByUserIdAndProductId(user.getId(),product.getId());
		if(avail==null) {
		
			CartItem cartItem=new CartItem();
			cartItem.setProduct(product);
			cartItem.setUser(user);
			cartItem.setQuantity(quantity);
			cartItemRepository.save(cartItem);	
		}else {
			avail.setQuantity(quantity);
			cartItemRepository.save(avail);
			
		}
	}
	

}
