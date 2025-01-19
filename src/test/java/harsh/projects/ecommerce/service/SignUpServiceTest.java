package harsh.projects.ecommerce.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import harsh.projects.ecommerce.exception.UserAlreadyExistsException;
import harsh.projects.ecommerce.model.Login;
import harsh.projects.ecommerce.model.NewUser;
import harsh.projects.ecommerce.model.SignUpRequest;
import harsh.projects.ecommerce.model.User;

class SignUpServiceTest {

	// Data Setup
	// Sample test Data to check against or use. 
	int id = 1;
	String userName = "johndoe";
	String passWord = "password123";
	String firstName = "John";
	String lastName = "Doe";
	String email = "johndoe@example.com";
	String phone = "1234567890";
	
	
	@Test
	void testSignUp_UserAlreadyExistsException() {
		
		// setUp request
		NewUser user = new NewUser();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPhone(phone);
		
		Login login = new Login();
		login.setUsername(userName);
		login.setPassword(passWord);
		
		SignUpRequest signup_Request = new SignUpRequest();
		signup_Request.setCredentials(login);
		signup_Request.setUser(user);
		
		assertThrows(UserAlreadyExistsException.class, () -> {
			SignUpService.signUp(signup_Request);
		});
		
	}

}
