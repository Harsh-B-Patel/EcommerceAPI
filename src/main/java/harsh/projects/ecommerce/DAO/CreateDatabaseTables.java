package harsh.projects.ecommerce.DAO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import harsh.projects.ecommerce.service.Constants;

public class CreateDatabaseTables {

	
	//Connect to DB method
	public static void db_Connect (String Query) {

		// Add ENV Constants
        String DB_URL = Constants.DB_URL;
        String DB_USER = Constants.DB_USER;
        String DB_PASSWORD = Constants.DB_PASSWORD;
        ResultSet rs = null;
        
       
        try {
        	
        	 // Connect to DB 
        	Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        	Statement statement = connection.createStatement();
        	
        	
        	// execute to DB method
        	rs = statement.executeQuery(Query);
        	Boolean b = statement.execute(Query);


        	
        }
        
        catch(Exception e ) {
        	e.printStackTrace();
        }
        
		//return null;
		
	}
	

	
	
}
