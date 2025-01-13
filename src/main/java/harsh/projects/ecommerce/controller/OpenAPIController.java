package harsh.projects.ecommerce.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class OpenAPIController {
    

	/**
	 * Extract JSON from file as string.
	 * Use JsonNode to convert String to JSON.   
	 * @return JsonNode Object (json)
	 */
    @GetMapping("/")
    public JsonNode defaultPage() {
		
        String filePath = "resources/openapi.json"; // Path File
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
    
}