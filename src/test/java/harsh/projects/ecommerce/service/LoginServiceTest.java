package harsh.projects.ecommerce.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import harsh.projects.ecommerce.exception.UserDoesNotExistsException;
import harsh.projects.ecommerce.model.Login;
import harsh.projects.ecommerce.model.LoginResponse;
import harsh.projects.ecommerce.model.Token;
import harsh.projects.ecommerce.model.User;

class LoginServiceTest {
	
	String token = JwtUtil.generateToken(Constants.SUBJECT, "johndoe");
	// Sample test Data to check against or use. 
	int id = 1;
	String userName = "johndoe";
	String passWord = "password123";
	String firstName = "John";
	String lastName = "Doe";
	String email = "johndoe@example.com";
	String phone = "1234567890";

	@Test
	void testLoginSuccess() throws UserDoesNotExistsException {
		// Setup to check against , User 
		User user = new User();
		user.setId(id);
		user.setUsername(userName);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPhone(phone);
		
		// LoginService run
		Login login = new Login();
		login.setUsername(userName);
		login.setPassword(passWord);

		LoginResponse loginResponseActual = LoginService.Login(login);
		User userResult = loginResponseActual.getUser();
		
		assertEquals(user, userResult);
	}

	@Test
	void testLoginFailure() {
		//  Test if invalid creentials and what errors thworn for it 
		fail("Not yet implemented");
	}
	
}
