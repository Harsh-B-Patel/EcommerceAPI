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

public class DaoUtil {

	// Add ENV Constants
	static String DB_URL = Constants.DB_URL;
	static String DB_USER = Constants.DB_USER;
	static String DB_PASSWORD = Constants.DB_PASSWORD;

	/**
	 * Database call to check if username already exist in DB
	 * @param username
	 * @return
	 */
	public static boolean checkLogin(String username) {
		String query = "SELECT 1 FROM user WHERE username='" + username + "';";

		boolean user_Exists = false;
		try {

			// Connect to DB
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement statement = connection.prepareStatement(query);

			// execute to DB method
			// Null exists in DB so below is useless we need better check for username
			// ResultSet with manual check
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
	 * Database call to retrive user details
	 * @param username
	 * @return
	 */
	public static User getUserDetails(String username) {
		String query = "SELECT * FROM user WHERE username='" + username + "';";
		try {

			// Connect to DB
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			Statement statement = connection.createStatement();

			System.out.println("DaoUtil.getUserDetails: Connection to DB established");
			// execute to DB method
			ResultSet rs = statement.executeQuery(query);
			System.out.println("DaoUtil.getUserDetails: Made Query to DB");

			// Map ResultSet to object Manually
			User user = new User();
			while (rs.next()) {
				user.setId((long) rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));

			}
			System.out.println("DaoUtil.getUserDetails: Map to POJO:User");

			System.out.println("DaoUtil.getUserDetails: Return User");
			return user;
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
					"INSERT INTO User (password, username, firstname, lastname, email, phone) VALUES (?,?,?,?,?,?)");
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
			User addedUser = DaoUtil.getUserDetails(credentials.getUsername());

			System.out.println("DaoUtil.setUserDetails: Return User");
			return addedUser;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}


}
