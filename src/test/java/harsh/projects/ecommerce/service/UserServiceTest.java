package harsh.projects.ecommerce.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import harsh.projects.ecommerce.exception.TokenInValidException;
import harsh.projects.ecommerce.exception.UserDoesNotExistsException;
import harsh.projects.ecommerce.model.User;

class UserServiceTest {

	// Sample test Data to check against or use.
	int id = 1;
	String userName = "johndoe";
	String passWord = "password123";
	String firstName = "John";
	String lastName = "Doe";
	String email = "johndoe@example.com";
	String phone = "1234567890";
	String token = JwtUtil.generateToken(Constants.SUBJECT, "johndoe");

	
	@Test
	void testGetUserByUserID() throws TokenInValidException, UserDoesNotExistsException {
		// Setup to check against , User
		User user = new User();
		user.setId(id);
		user.setUsername(userName);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPhone(phone);
		
		assertEquals(user, UserService.GetUserByUserID(id, token));		
		
	}
	
	@Test
	void testGetUserByUserID_UserDoesNotExistException() throws TokenInValidException, UserDoesNotExistsException {
		// Setup to check against , User
		User user = new User();
		user.setId(id);
		user.setUsername(firstName);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPhone(phone);
		
		//assertEquals(user, UserService.GetUserByUserID(id, token));		
		
		assertThrows(UserDoesNotExistsException.class, () -> {
			UserService.GetUserByUserID(99879, token);
		}); // Lamda
		
	}

	@Test
	void testGetUserByUserID_InvalidTokenException() {
		// Setup to check against , User
		User user = new User();
		user.setId(id);
		user.setUsername(firstName);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPhone(phone);
		
		//assertEquals(user, UserService.GetUserByUserID(id, token));		
		
		assertThrows(UserDoesNotExistsException.class, () -> {
			UserService.GetUserByUserID(99879, token);
		}); // Lamda
	}


}
