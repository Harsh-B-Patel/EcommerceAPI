package harsh.projects.ecommerce.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import harsh.projects.ecommerce.DAO.DaoUtil;
import harsh.projects.ecommerce.exception.TokenInValidException;
import harsh.projects.ecommerce.exception.UserDoesNotExistsException;
import harsh.projects.ecommerce.model.User;
import jakarta.validation.Valid;

public class UserService {

	/**
	 * Validates Jwt Token
	 * Returns user information from userID
	 * @param userId
	 * @param token
	 * @return
	 * @throws TokenInValidException
	 * @throws UserDoesNotExistsException
	 */
	public static User GetUserByUserID(int userId, String token)
			throws TokenInValidException, UserDoesNotExistsException {

		System.out.println("Enter UserService.GetUserByUserID");

		// Validate Token
		JwtUtil.validateToken(token);

		// check id user id exists
		if (DaoUtil.checkUserByID(userId)) {
			// user exists
			// Get User Detals
			System.out.println("UserService.GetUserByUserID: Get User Details");
			User userDetails = DaoUtil.getUserDetailsById(userId);

			System.out.println("UserService.GetUserByUserID: Return User Details");
			return userDetails;
		} else {
			throw new UserDoesNotExistsException("User with ID: " + userId + " does not exists");
		}
	}
	
	
	
	public static User PostUserByUserID(int userId, String token, User user)
			throws TokenInValidException, UserDoesNotExistsException {

		System.out.println("Enter UserService.PostUserByUserID");

		// Validate Token
		JwtUtil.validateToken(token);
	
		// Validate userId == User.getId()
		if (userId != user.getId()) {
			throw new UserDoesNotExistsException("UserID provided in URl does not match UserId in RequestBody"); 
		}
		
		// check if id user exists
		if (DaoUtil.checkUserByID(userId)) {
			
			// Pass new User Details to DB and Return updated user back
			User userDetails = DaoUtil.updateUserDetails(user);

			System.out.println("UserService.PostUserByUserID: Return User Details");
			return userDetails;
		} else {
			throw new UserDoesNotExistsException("User with ID: " + userId + " does not exists");
		}
	}
	
}
