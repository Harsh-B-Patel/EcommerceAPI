package harsh.projects.ecommerce.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import harsh.projects.ecommerce.model.Product;
import harsh.projects.ecommerce.model.User;
import harsh.projects.ecommerce.service.Constants;

public class ProductDaoUtil {

	// Add ENV Constants
	static String DB_URL = Constants.DB_URL;
	static String DB_USER = Constants.DB_USER;
	static String DB_PASSWORD = Constants.DB_PASSWORD;

	// Check productID
	/**
	 * Database call to check if query Product item already exist in DB
	 * 
	 * @param username
	 * @return
	 */
	public static boolean checkProduct(int id) {
		System.out.println("ProductDaoUtil.checkProduct : check id exists");

		boolean product_Exists = false;
		String query = "SELECT 1 FROM product WHERE id='" + id + "';";

		try {

			// Connect to DB
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement statement = connection.prepareStatement(query);

			// execute to DB method
			// MySQL retuns Null set if !exists in DB so ResultSet with manual check
			ResultSet rs = statement.executeQuery(query);

			// Check if ResultSet is empty
			if (rs.next()) {
				product_Exists = true;
				System.out.println("ProductDaoUtil.checkProduct: Product found");
			} else {
				product_Exists = false;
				System.out.println("ProductDaoUtil.checkProduct: Product Not found");
			}

			return product_Exists;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product_Exists;
	}

	// get productDetailsbyID

	/**
	 * Database call to retrive product details
	 * 
	 * @param username
	 * @return
	 */
	public static Product getProductDetails(int id) {
		String query = "SELECT * FROM product WHERE id='" + id + "';";
		try {

			// Connect to DB
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			Statement statement = connection.createStatement();

			System.out.println("ProductDaoUtil.getProductDetails: Connection to DB established");
			// execute to DB method
			ResultSet rs = statement.executeQuery(query);
			System.out.println("ProductDaoUtil.getProductDetails: Made Query to DB");

			// Map ResultSet to object Manually
			Product product = new Product();
			while (rs.next()) {
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setPrice(rs.getDouble("price"));
				product.setStock(rs.getInt("stock"));
				product.setCategoryName(rs.getString("categoryName"));

			}
			System.out.println("DaoUtil.getUserDetailsByID: Map to POJO:User");

			System.out.println("DaoUtil.getUserDetailsByID: Return User");
			return product;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// Update productDetailsbyID
	/**
	 * Database call to update product details
	 * 
	 * @param username
	 * @return
	 */
	public static Product updateProductDetails(int id, Product product) {

		try {
			// Connect to DB
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			System.out.println("ProductDaoUtil.updateProductDetails: Connection to DB established");

			// Add some kind of meausre here that it will fetch existing details and fill out fields with are null. Or make all fields mandatory
			
			// Prepare the the query
			PreparedStatement statement = connection.prepareStatement(
					"UPDATE product SET name = ?, description = ?, price = ?, stock = ?, categoryName = ? WHERE id = ? ");
			statement.setString(1, product.getName());
			statement.setString(2, product.getDescription());
			statement.setDouble(3, product.getPrice());
			statement.setInt(4, product.getStock());
			statement.setString(5, product.getCategoryName());
			statement.setInt(6, product.getId());
			System.out.println("ProductDaoUtil.updateProductDetails: Query prepared");

			// Update to DB
			System.out.println("ProductDaoUtil.updateProductDetails: Updated User");
			boolean isAdded = statement.execute();
			if (isAdded) {
				System.out.println("ProductDaoUtil.updateProductDetails: Updated User succesfully");
			} else {
				System.out.println("ProductDaoUtil.updateProductDetails: Updated User not Succesfull");
			}

			// Retrieve the newly added user from DB
			System.out.println("ProductDaoUtil.updateProductDetails: Get Newly added User");
			Product updatedProduct = ProductDaoUtil.getProductDetails(id);

			System.out.println("ProductDaoUtil.updateProductDetails: Return User");
			return updatedProduct;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

// Delete productDetailsbyID
	/**
	 * Database call to delete product details
	 * 
	 * @param username
	 * @return
	 */
	public static boolean deleteProduct(int id) {

		try {
			// Connect to DB
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			System.out.println("ProductDaoUtil.deleteProduct: Connection to DB established");

			// Prepare the the query
			PreparedStatement statement = connection.prepareStatement("DELETE FROM product WHERE id = ?");
			statement.setInt(1, id);
			statement.execute();
			boolean isDeleted = ProductDaoUtil.checkProduct(id);
			if (!isDeleted) {
				System.out.println("ProductDaoUtil.deleteProduct: Deleted User succesfully");
				return true;
			} else {
				System.out.println("ProductDaoUtil.deleteProduct: Deleted User not Succesfull");
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

}
