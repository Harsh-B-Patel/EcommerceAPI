package harsh.projects.ecommerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import harsh.projects.ecommerce.exception.TokenInValidException;
import harsh.projects.ecommerce.exception.UserDoesNotExistsException;
import harsh.projects.ecommerce.model.Cart;
import harsh.projects.ecommerce.service.CartService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user/{userId}/cart")
public class CartController {
	
	/**
	 * Get Cart info for customer based on Customer UserID
	 * @param userId
	 * @param token
	 * @return
	 * @throws UserDoesNotExistsException 
	 * @throws TokenInValidException 
	 */
	@GetMapping()
	public Cart GetCartInfo(@Valid @PathVariable("userId") int userId, @RequestHeader ("token") String token) throws TokenInValidException, UserDoesNotExistsException {
		return CartService.GetCartByUserID(userId, token);
		
	}
	
	/**
	 * Update Cart with info provided for Customer
	 * @param userId
	 * @param token
	 * @return
	 * @throws UserDoesNotExistsException 
	 * @throws TokenInValidException 
	 */
	@PutMapping()
	public Cart PutCartInfo(@Valid @PathVariable("userId") int userId, @RequestHeader ("token") String token, @RequestBody Cart cart) throws TokenInValidException, UserDoesNotExistsException {
		// update cartItems db , this will only update stock, add or remove entry (userdid, productid, quantuity)
		// will just be using ids : userId and ProductId to update table (primary keys) update quantity
		return CartService.UpdateCart(userId, cart, token);
		
	}

}
