package harsh.projects.ecommerce.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.fasterxml.jackson.databind.ObjectMapper;

import harsh.projects.ecommerce.model.Login;
import harsh.projects.ecommerce.model.NewUser;
import harsh.projects.ecommerce.model.User;
import harsh.projects.ecommerce.service.Constants;
import jakarta.validation.Valid;

public class UserDaoUtil {

	// Add ENV Constants
	static String DB_URL = Constants.DB_URL;
	static String DB_USER = Constants.DB_USER;
	static String DB_PASSWORD = Constants.DB_PASSWORD;


	/**
	 * Database call to check if username already exist in DB
	 * @param username
	 * @return
	 */
	public static boolean checkUserByUsername(String username) {
		String query = "SELECT 1 FROM user WHERE username='" + username + "';";
		return UserDaoUtil.checkUser(query);
	}
	
	
	/**
	 * Database call to check if username already exist in DB
	 * @param username
	 * @return
	 */
	public static boolean checkUserByID(int id) {
		String query = "SELECT 1 FROM user WHERE id='" + id + "';";	
		return UserDaoUtil.checkUser(query);
	}
	
	
	/**
	 * Database call to check if query User item already exist in DB
	 * @param username
	 * @return
	 */
	public static boolean checkUser(String query) {
		System.out.println("DaoUtil.checkUser: check User exists");

		boolean user_Exists = false;
		
		try {

			// Connect to DB
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement statement = connection.prepareStatement(query);

			// execute to DB method
			// MySQL retuns Null set if !exists in DB so ResultSet with manual check
			ResultSet rs = statement.executeQuery(query);

			// Check if ResultSet is empty
			if (rs.next()) {
				user_Exists = true;
				System.out.println("DaoUtil.checkLogin: User found");
			} else {
				user_Exists = false;
				System.out.println("DaoUtil.checkLogin: User Not found");
			}

			return user_Exists;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user_Exists;
	}
	
	
	/**
	 * Pre Req: User exists 
	 * Returns if Credentials exists or not
	 * @param credentials
	 * @return
	 */
	public static boolean verifyLoginCredentials(Login credentials) {
		boolean valid_credentials = false;
		try {

			// Connect to DB
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			// Prepare the the query
			PreparedStatement statement = connection
					.prepareStatement("SELECT 1 FROM user WHERE username = ? and password = ? ");
			statement.setString(1, credentials.getUsername());
			statement.setString(2, credentials.getPassword());

			// execute to DB method
			// MySQL retuns Null set if !exists in DB so ResultSet with manual check
			ResultSet rs = statement.executeQuery();

			// Check if ResultSet is empty
			if (rs.next()) {
				valid_credentials = true;
				System.out.println("CartDaoUtil.itemExists: item found");
			} else {
				valid_credentials = false;
				System.out.println("CartDaoUtil.itemExists: item Not found");
			}

			return valid_credentials;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valid_credentials;
		
	}
	
	/**
	 * Database call to retrive user details by username
	 * @param username
	 * @return
	 */
	public static User getUserDetails(String username) {
		System.out.println("DaoUtil.getUserDetails: Get User Details by username");
		
		String query = "SELECT * FROM user WHERE username='" + username + "';";
		try {

			User user = UserDaoUtil.getUserDetailsQueryExecution(query);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
	
	/**
	 * Database call to retrive user details by ID
	 * @param username
	 * @return
	 */
	public static User getUserDetailsById(int id) {
		System.out.println("DaoUtil.getUserDetailsById: Get User Details by id");
		
		String query = "SELECT * FROM user WHERE id='" + id + "';";
		try {

			User user = UserDaoUtil.getUserDetailsQueryExecution(query);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
	
	
	
	/**
	 * Database call to retrive user details based on query
	 * @param username
	 * @return
	 */
	public static User getUserDetailsQueryExecution(String query) {
		
		try {

			// Connect to DB
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			Statement statement = connection.createStatement();

			System.out.println("DaoUtil.getUserDetailsByID: Connection to DB established");
			// execute to DB method
			ResultSet rs = statement.executeQuery(query);
			System.out.println("DaoUtil.getUserDetailsByID: Made Query to DB");

			// Map ResultSet to object Manually
			User user = new User();
			while (rs.next()) {
				user.setId((int) rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));

			}
			System.out.println("DaoUtil.getUserDetailsByID: Map to POJO:User");

			System.out.println("DaoUtil.getUserDetailsByID: Return User");
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	
	
	
	/**
	 * Database call to update user details to USER Table in DB
	 * 
	 * @param username
	 * @return
	 */
	public static User updateUserDetails(User user) {
		boolean isAdded = false;
		try {

			// Connect to DB
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			System.out.println("DaoUtil.updateUserDetails: Connection to DB established");

			// Prepare the the query
			PreparedStatement statement = connection.prepareStatement(
					"UPDATE user SET username = ?, firstname = ?, lastname = ?, email = ?, phone = ? WHERE id = ? ");
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getFirstName());
			statement.setString(3, user.getLastName());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getPhone());
			statement.setInt(6, user.getId());
			System.out.println("DaoUtil.updateUserDetails: Query prepared");

			// Update to DB
			System.out.println("DaoUtil.updateUserDetails: Updated User");
			isAdded = statement.execute();
			if (isAdded) {
				System.out.println("DaoUtil.updateUserDetails: Updated User succesfully");
			} else {
				System.out.println("DaoUtil.updateUserDetails: Updated User not Succesfull");
			}

			// Retrieve the newly added user from DB
			System.out.println("DaoUtil.setUserDetails: Get Newly added User");
			User updatedUser = UserDaoUtil.getUserDetailsById(user.getId());

			System.out.println("DaoUtil.setUserDetails: Return User");
			return updatedUser;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
	

	/**
	 * Database call to add user details to USER Table in DB
	 * 
	 * @param username
	 * @return
	 */
	public static User setUserDetails(Login credentials, NewUser userDetails) {
		boolean isAdded = false;
		try {

			// Connect to DB
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			System.out.println("DaoUtil.setUserDetails: Connection to DB established");

			// Prepare the the query
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO user (password, username, firstname, lastname, email, phone) VALUES (?,?,?,?,?,?)");
			statement.setString(1, credentials.getPassword());
			statement.setString(2, credentials.getUsername());
			statement.setString(3, userDetails.getFirstName());
			statement.setString(4, userDetails.getLastName());
			statement.setString(5, userDetails.getEmail());
			statement.setString(6, userDetails.getPhone());
			System.out.println("DaoUtil.setUserDetails: Query prepared");

			// Add to DB
			System.out.println("DaoUtil.setUserDetails: Adding User");
			isAdded = statement.execute();
			if (isAdded) {
				System.out.println("DaoUtil.setUserDetails: Added User succesfully");
			} else {
				System.out.println("DaoUtil.setUserDetails: Adding User not Succesfull");
			}

			// Retrieve the newly added user from DB
			System.out.println("DaoUtil.setUserDetails: Get Newly added User");
			User addedUser = UserDaoUtil.getUserDetails(credentials.getUsername());

			System.out.println("DaoUtil.setUserDetails: Return User");
			return addedUser;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
	
	/**
	 * Database call to retrive user details based on query
	 * @param username
	 * @return
	 */
	public static Boolean deleteUser(int id) {
		
		try {

			// Connect to DB
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			System.out.println("DaoUtil.deleteUser: Connection to DB established");

			// Prepare the the query
			PreparedStatement statement = connection.prepareStatement(
								"DELETE FROM user WHERE id = ?");
			statement.setInt(1, id);
			statement.execute();
			boolean isDeleted = UserDaoUtil.checkUserByID(id);
			if (!isDeleted) {
				System.out.println("DaoUtil.deleteUser: Deleted User succesfully");
				return true;
			} else {
				System.out.println("DaoUtil.deleteUser: Deleted User not Succesfull");
				return false;
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	
	
	


}
