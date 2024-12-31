package harsh.projects.ecommerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import harsh.projects.ecommerce.model.Cart;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user/{userId}/cart")
public class CartController {
	
	/**
	 * Get Cart info for customer
	 * @param userId
	 * @param token
	 * @return
	 */
	@GetMapping()
	public Cart GetCartInfo(@Valid @PathVariable("userId") int userId, @RequestHeader ("token") String token) {
		
		return null;
		
	}
	
	/**
	 * Update Cart with info provided for Customer
	 * @param userId
	 * @param token
	 * @return
	 */
	@PutMapping()
	public Cart PutCartInfo(@Valid @PathVariable("userId") int userId, @RequestHeader ("token") String token, @RequestBody Cart cart) {
		// update cartItems db , this will only update stock, add or remove entry (userdid, productid, quantuity)
		// will just be using ids : userId and ProductId to update table (primary keys) update quantity
		return null;
		
	}

}
