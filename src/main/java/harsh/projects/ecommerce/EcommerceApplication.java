package harsh.projects.ecommerce;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import harsh.projects.ecommerce.DAO.CreateDatabaseTables;

@SpringBootApplication
@RestController
@RequestMapping("/")
public class EcommerceApplication {


	/**
	 * Extract JSON from file as string.
	 * Use JsonNode to convert String to JSON.   
	 * @return JsonNode Object (json)
	 */
	@GetMapping("/")
	public JsonNode defaultPage() {
		
        String filePath = "openapi.json"; // Replace with the path to your Java file
        String fileContent = "";
        JsonNode jsonNode = null;
        
        try {
            // Read file content as a string
        	fileContent = Files.readString(Path.of(filePath));
        	
        	// String to JsonNode
    		ObjectMapper mapper = new ObjectMapper();
    		jsonNode = mapper.readTree(fileContent);
           
            // Print the file content for logging
            System.out.println(fileContent);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		
        // Return Json back to consumer
		return jsonNode;
		
	}
	
	public static void main(String[] args) throws IOException, SQLException {
		SpringApplication.run(EcommerceApplication.class, args);
		
		// Create Database if not already
		db_Create();
		
	}
	
	public static void db_Create() throws IOException, SQLException {
		CreateDatabaseTables DB = new CreateDatabaseTables();

	}
	

}
