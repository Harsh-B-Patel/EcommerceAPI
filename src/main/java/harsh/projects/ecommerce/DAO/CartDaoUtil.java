package harsh.projects.ecommerce.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import harsh.projects.ecommerce.model.Cart;
import harsh.projects.ecommerce.model.CartItem;
import harsh.projects.ecommerce.model.Product;
import harsh.projects.ecommerce.service.Constants;

public class CartDaoUtil {

	// Add ENV Constants
	static String DB_URL = Constants.DB_URL;
	static String DB_USER = Constants.DB_USER;
	static String DB_PASSWORD = Constants.DB_PASSWORD;

	// Check if Cart Exists
	/**
	 * Database call to check if Cart exist in DB
	 * 
	 * @param username
	 * @return
	 */
	public static boolean checkCartExists(int id) {
		System.out.println("CartDaoUtil.checkCartExists : check if cart exists");

		boolean cart_Exists = false;
		String query = "SELECT 1 FROM cartitem WHERE userid='" + id + "';";

		try {

			// Connect to DB
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement statement = connection.prepareStatement(query);

			// execute to DB method
			// MySQL retuns Null set if !exists in DB so ResultSet with manual check
			ResultSet rs = statement.executeQuery(query);

			// Check if ResultSet is empty
			if (rs.next()) {
				cart_Exists = true;
				System.out.println("CartDaoUtil.checkCartExists: cart found");
			} else {
				cart_Exists = false;
				System.out.println("CartDaoUtil.checkCartExists: cart Not found");
			}

			return cart_Exists;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cart_Exists;
	}

	// Retrieve Cart Details
	/**
	 * Database call to Get Cart From DB
	 * 
	 * @param username
	 * @return
	 */
	public static Cart getCart(int Id) {
		String query = "SELECT * FROM cartitem WHERE userId='" + Id + "';";
		try {

			// Connect to DB
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			Statement statement = connection.createStatement();

			System.out.println("CartDaoUtil.getCart: Connection to DB established");
			// execute to DB method
			ResultSet rs = statement.executeQuery(query);
			System.out.println("CartDaoUtil.getCart: Made Query to DB");

			// Map ResultSet to object Manually
			Cart cart = new Cart();
			cart.setUserId(Id);

			while (rs.next()) {
				// Set Data
				int quantity = rs.getInt("quantity");

				// Call Product DAO with Product ID and Set Product
				Product product = new Product();
				product = ProductDaoUtil.getProductDetails(rs.getInt("productId"));

				// Add Product and Quantity to cart.CartItems Array
				CartItem item = new CartItem();
				item.setProduct(product);
				item.setQuantity(quantity);
				cart.getCartItems().add(item);

			}
			System.out.println("CartDaoUtil.getCart: Map to POJO: Cart");

			System.out.println("CartDaoUtil.getCart: Return Cart");
			return cart;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Cart updateCart(int id, Cart cart) {
		try {
			// Connect to DB
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			System.out.println("CartDaoUtil.updateCart: Connection to DB established");

			// loop over cartItems Array , each product
			for (CartItem item : cart.getCartItems()) {

				// Check if Item already exists in DB
				if (CartDaoUtil.itemExists(id, item.getProduct().getId()) && item.getQuantity() >= 0) {

					if (item.getQuantity() == 0) {
						// Quantity == 0, remove row
						CartDaoUtil.itemDelete(id, item.getProduct().getId());
						System.out.println("CartDaoUtil.updateCart: Quantity 0 : Delete Product with ID "
								+ item.getProduct().getId());
					} else {
						// Quantity != 0 , Update Quantity,
						CartDaoUtil.itemUpdate(id, item.getProduct().getId(), item.getQuantity());
						System.out.println("CartDaoUtil.updateCart: updated Product Quantity in with ID "
								+ item.getProduct().getId());
					}

				} else {
					// New Item - Insert into Db
					if (item.getQuantity() > 0) {
						CartDaoUtil.itemInsert(id, item.getProduct().getId(), item.getQuantity());
						System.out.println("CartDaoUtil.updateCart: Inserted Product in cart with ID "
								+ item.getProduct().getId());

					} else {
						System.out.println("CartDaoUtil.updateCart: New Quantity cannot be less than 0 or 0");
					}
				}
			}

			// Retrieve the cart after updates from DB
			System.out.println("CartDaoUtil.updateCart: Get Updated Cart");
			Cart updatedCart = CartDaoUtil.getCart(id);

			System.out.println("CartDaoUtil.updateCarts: Return Cart");
			return updatedCart;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	// Insert new Item

	public static void itemInsert(int userId, int productId, int quantity) {
		try {
			// Connect to DB
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			System.out.println("CartDaoUtil.itemInsert: Connection to DB established");

			// Prepare the the query
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO cartitem (userId, productId, quantity) VALUES (?,?,?)");
			statement.setInt(1, userId);
			statement.setInt(2, productId);
			statement.setInt(3, quantity);
			System.out.println("CartDaoUtil.itemInsert: Query prepared");

			// Update to DB
			System.out.println("CartDaoUtil.itemInsert: Inserted Item");
			boolean isAdded = statement.execute();
			if (isAdded) {
				System.out.println("CartDaoUtil.itemInsert: Inserted Item succesfully");
			} else {
				System.out.println("CartDaoUtil.itemInsert: Inserted Item not Succesfull");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Update Existing Item
	public static void itemUpdate(int userId, int productId, int quantity) {
		try {
			// Connect to DB
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			System.out.println("CartDaoUtil.itemUpdate: Connection to DB established");

			// Add some kind of meausre here that it will fetch existing details and fill
			// out fields with are null. Or make all fields mandatory

			// Prepare the the query
			PreparedStatement statement = connection
					.prepareStatement("UPDATE cartitem SET quantity = ? WHERE userId = ? and productId = ? ");
			statement.setInt(1, quantity);
			statement.setInt(2, userId);
			statement.setInt(3, productId);
			System.out.println("CartDaoUtil.itemUpdate: Query prepared");

			// Update to DB
			System.out.println("CartDaoUtil.itemUpdate: Updated Item");
			boolean isAdded = statement.execute();
			if (isAdded) {
				System.out.println("CartDaoUtil.itemUpdate: Updated Item succesfully");
			} else {
				System.out.println("CartDaoUtil.itemUpdate: Updated Item not Succesfull");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Update cart - when quantity = 0;
	// delete row
	public static void itemDelete(int userId, int productId) {
		System.out.println("CartDaoUtil.itemDelete: ");
		try {
			// Connect to DB
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			System.out.println("CartDaoUtil.itemDelete: Connection to DB established");

			// Prepare the the query
			PreparedStatement statement = connection
					.prepareStatement("DELETE FROM CartItem WHERE userid = ? and productId = ? ");
			statement.setInt(1, userId);
			statement.setInt(2, productId);
			System.out.println(statement.toString());
			statement.execute();
			System.out.println(statement.toString());
			boolean isDeleted = CartDaoUtil.itemExists(userId, productId);
			if (!isDeleted) {
				System.out.println("CartDaoUtil.itemDelete: Deleted item succesfully");

			} else {
				System.out.println("CartDaoUtil.itemDelete: Deleted item not Succesfull");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static boolean itemExists(int userId, int productId) {

		System.out.println("CartDaoUtil.itemExists : check if item exists");

		boolean item_Exists = false;
		//String query = "SELECT 1 FROM cartitem WHERE userId='" + userId + "' and productId ='" + productId + " ';"

		try {

			// Connect to DB
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			// Prepare the the query
			PreparedStatement statement = connection
					.prepareStatement("SELECT 1 FROM cartitem WHERE userid = ? and productId = ? ");
			statement.setInt(1, userId);
			statement.setInt(2, productId);

			// execute to DB method
			// MySQL retuns Null set if !exists in DB so ResultSet with manual check
			ResultSet rs = statement.executeQuery();

			// Check if ResultSet is empty
			if (rs.next()) {
				item_Exists = true;
				System.out.println("CartDaoUtil.itemExists: item found");
			} else {
				item_Exists = false;
				System.out.println("CartDaoUtil.itemExists: item Not found");
			}

			return item_Exists;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return item_Exists;
	}

}
