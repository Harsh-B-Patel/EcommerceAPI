package harsh.projects.ecommerce.DAO;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import harsh.projects.ecommerce.model.Login;
import harsh.projects.ecommerce.model.NewUser;
import harsh.projects.ecommerce.model.User;

class UserDaoUtilTest {
	
	// Sample test Data to check against or use. 
	int id = 1;
	String userName = "johndoe";
	String passWord = "password123";
	String firstName = "John";
	String lastName = "Doe";
	String email = "johndoe@example.com";
	String phone = "1234567890";
	

	@Test
	void testCheckUserByUsername() {
		assertTrue(UserDaoUtil.checkUserByUsername("johndoe"));
		assertFalse(UserDaoUtil.checkUserByUsername("Jackie"));		
	}

	@Test
	void testCheckUserByID() {
		assertTrue(UserDaoUtil.checkUserByID(1));
	}


	@Test
	void testGetUserDetails() {
		User user = new User();
		user.setId(id);
		user.setUsername(userName);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPhone(phone);
		
		assertEquals(user, UserDaoUtil.getUserDetails(userName));
		
	}

	@Test
	void testGetUserDetailsById() {
		
		User user = new User();
		user.setId(id);
		user.setUsername(userName);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPhone(phone);
		
		assertEquals(user, UserDaoUtil.getUserDetailsById(1));
	}





}
