package harsh.projects.ecommerce.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import harsh.projects.ecommerce.DAO.UserDaoUtil;
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
		if (UserDaoUtil.checkUserByID(userId)) {
			// user exists
			// Get User Detals
			System.out.println("UserService.GetUserByUserID: Get User Details");
			User userDetails = UserDaoUtil.getUserDetailsById(userId);

			System.out.println("UserService.GetUserByUserID: Return User Details");
			return userDetails;
		} else {
			throw new UserDoesNotExistsException("User with ID: " + userId + " does not exists");
		}
	}
	
	
	/**
	 * Validates Jwt Token
	 * Updates User details
	 * @param userId
	 * @param token
	 * @param user
	 * @return
	 * @throws TokenInValidException
	 * @throws UserDoesNotExistsException
	 */
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
		if (UserDaoUtil.checkUserByID(userId)) {
			
			// Pass new User Details to DB and Return updated user back
			User userDetails = UserDaoUtil.updateUserDetails(user);

			System.out.println("UserService.PostUserByUserID: Return User Details");
			return userDetails;
		} else {
			throw new UserDoesNotExistsException("User with ID: " + userId + " does not exists");
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
	public static String DeleteUserByUserID(int userId, String token)
			throws TokenInValidException, UserDoesNotExistsException {

		System.out.println("Enter UserService.DeleteUserByUserID");

		// Validate Token
		JwtUtil.validateToken(token);

		// check if user id exists
		if (UserDaoUtil.checkUserByID(userId)) {
			// user exists : OK 
			// Delete User Detals
			System.out.println("UserService.DeleteUserByUserID: Delete User Details");
			boolean isDeleted = UserDaoUtil.deleteUser(userId);
			
			if(isDeleted) {
				System.out.println("UserService.DeleteUserByUserID: Deleted User Details");
				return "User: " + userId + " Succesfully deleted";
			}

		} else {
			throw new UserDoesNotExistsException("User with ID: " + userId + " does not exists");
		}
		return "User: \" + userId + \" Succesfully deleted";
		
	}
	
	
	
	
}
