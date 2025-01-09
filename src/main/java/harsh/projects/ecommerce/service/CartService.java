package harsh.projects.ecommerce.service;

import harsh.projects.ecommerce.DAO.CartDaoUtil;
import harsh.projects.ecommerce.DAO.ProductDaoUtil;
import harsh.projects.ecommerce.DAO.UserDaoUtil;
import harsh.projects.ecommerce.exception.TokenInValidException;
import harsh.projects.ecommerce.exception.UserDoesNotExistsException;
import harsh.projects.ecommerce.model.Cart;
import harsh.projects.ecommerce.model.Product;

public class CartService {
	
	/**
	 * Validates Jwt Token
	 * Returns Cart information based on UserID
	 * @param userId
	 * @param token
	 * @return
	 * @throws TokenInValidException
	 * @throws UserDoesNotExistsException
	 */
	public static Cart GetCartByUserID(int Id, String token)
			throws TokenInValidException, UserDoesNotExistsException {

		System.out.println("Enter CartService.GetCartByUserID");

		// Validate Token
		JwtUtil.validateToken(token);

		// check user id exists
		if (UserDaoUtil.checkUserByID(Id)) {
			// User exists
			
			Cart cart = new Cart();
			// Check if Cart Exists for this user, if not. Return empty cart back (cart items will be null, Quantity = 0)
			if (CartDaoUtil.checkCartExists(Id)) {
				// Cart Exits 
				System.out.println("CartService.GetCartByUserID: Cart Exists");
				
				//Get Cart From DB
				cart = CartDaoUtil.getCart(Id);
				System.out.println("CartService.GetCartByUserID: Return Cart Details");
				return cart;
				
			}
			else {
				// Cart Does not Exists, Cart is empty with No Products
				System.out.println("CartService.GetCartByUserID: Cart Does Not Exists");
				
				//Create Cart with Empty CartItems ArrayList and UserID			
				cart.setUserId(Id);
				
				System.out.println("CartService.GetCartByUserID: Return Cart Details");
				return cart;			
			}


		} else {
			throw new UserDoesNotExistsException("User with ID: " + Id + " does not exists");
		}
	}
	
	
	/**
	 * Validates Jwt Token
	 * Updates Cart and returns Cart information back 
	 * @param userId
	 * @param token
	 * @return
	 * @throws TokenInValidException
	 * @throws UserDoesNotExistsException
	 */
	public static Cart UpdateCart(int Id, Cart cart, String token) 
			throws TokenInValidException, UserDoesNotExistsException {

		System.out.println("Enter CartService.UpdateCart");

		// Validate Token
		JwtUtil.validateToken(token);
		
		// Validate userId == User.getId()
		if (Id != cart.getUserId()) {
			throw new UserDoesNotExistsException("UserID provided in URl does not match UserID in RequestBody"); 
		}

		// check id user id exists
		if (UserDaoUtil.checkUserByID(Id)) {
			// Product exists
			// Get  Details
			System.out.println("CartService.UpdateCart: Update Cart Details");
			Cart updatedCart = CartDaoUtil.updateCart(Id, cart);

			System.out.println("CartService.UpdateCart: Return Cart Details");
			return updatedCart;
		} else {
			throw new UserDoesNotExistsException("Product with ID: " + Id + " does not exists");
		}
	}
	


}
