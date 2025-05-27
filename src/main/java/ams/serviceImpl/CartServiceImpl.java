package ams.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ams.DTO.CartItemDTO;
import ams.repository.CartItemRepository;

@Service
public class CartServiceImpl {

    @Autowired
    private CartItemRepository cartItemRepository;

    public List<CartItemDTO> getCartItemsByUserId(Long userId) {
        return cartItemRepository.findCartItemsByUserId(userId);
    }
    
}
