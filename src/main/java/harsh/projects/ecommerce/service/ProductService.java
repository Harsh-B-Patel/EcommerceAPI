package harsh.projects.ecommerce.service;

import harsh.projects.ecommerce.DAO.ProductDaoUtil;
import harsh.projects.ecommerce.DAO.UserDaoUtil;
import harsh.projects.ecommerce.exception.TokenInValidException;
import harsh.projects.ecommerce.exception.UserDoesNotExistsException;
import harsh.projects.ecommerce.model.Product;
import harsh.projects.ecommerce.model.User;

public class ProductService {

	
	/**
	 * Validates Jwt Token
	 * Returns product information from ID
	 * @param userId
	 * @param token
	 * @return
	 * @throws TokenInValidException
	 * @throws UserDoesNotExistsException
	 */
	public static Product GetProductByID(int Id, String token)
			throws TokenInValidException, UserDoesNotExistsException {

		System.out.println("Enter ProductService.GetProductByID");

		// Validate Token
		//JwtUtil.validateToken(token);

		// check id user id exists
		if (ProductDaoUtil.checkProduct(Id)) {
			// Product exists
			// Get  Details
			System.out.println("ProductService.GetProductByID: Get Product Details");
			Product productDetails = ProductDaoUtil.getProductDetails(Id);

			System.out.println("ProductService.GetProductByID: Return Product Details");
			return productDetails;
		} else {
			throw new UserDoesNotExistsException("Product with ID: " + Id + " does not exists");
		}
	}
	
	
	
	
	/**
	 * Validates Jwt Token
	 * Returns product information from ID
	 * @param userId
	 * @param token
	 * @return
	 * @throws TokenInValidException
	 * @throws UserDoesNotExistsException
	 */
	public static Product UpdateProduct(int Id, Product product, String token) 
			throws TokenInValidException, UserDoesNotExistsException {

		System.out.println("Enter ProductService.UpdateProduct");

		// Validate Token
		//JwtUtil.validateToken(token);
		
		// Validate userId == User.getId()
		if (Id != product.getId()) {
			throw new UserDoesNotExistsException("ProductID provided in URl does not match ProductId in RequestBody"); 
		}

		// check id user id exists
		if (ProductDaoUtil.checkProduct(Id)) {
			// Product exists
			// Get  Details
			System.out.println("ProductService.UpdateProduct: Update Product Details");
			Product productDetails = ProductDaoUtil.updateProductDetails(Id, product);

			System.out.println("ProductService.UpdateProduct: Return Product Details");
			return productDetails;
		} else {
			throw new UserDoesNotExistsException("Product with ID: " + Id + " does not exists");
		}
	}
	
	/**
	 * Validates Jwt Token
	 * Deletes user  from user Table
	 * @param userId
	 * @param token
	 * @return String
	 * @throws TokenInValidException
	 * @throws UserDoesNotExistsException
	 */
	public static String DeleteProduct(int Id, String token)
			throws TokenInValidException, UserDoesNotExistsException {

		System.out.println("Enter ProductService.DeleteProduct");

		// Validate Token
		//JwtUtil.validateToken(token);

		// check if user id exists
		if (UserDaoUtil.checkUserByID(Id)) {
			// product exists : OK 
			// Delete User Detals
			System.out.println("Enter ProductService.DeleteProduct: Delete User Details");
			boolean isDeleted = ProductDaoUtil.deleteProduct(Id);
			
			if(isDeleted) {
				System.out.println("Enter ProductService.DeleteProduct: Deleted User Details");
				return "Product: " + Id + " Succesfully deleted";
			}

		} else {
			throw new UserDoesNotExistsException("Product with ID: " + Id + " does not exists");
		}
		return "Product: \" + Id + \" Succesfully deleted";
		
	}
}
