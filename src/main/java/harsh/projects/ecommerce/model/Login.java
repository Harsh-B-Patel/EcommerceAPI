package harsh.projects.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonInclude(Include.NON_NULL) // Ignore null fields 
@JsonIgnoreProperties(ignoreUnknown = true) //ignore unknown fields 
public class Login {
	


	// Enforce Non Null and Non Blank 
	@NotBlank(message = "username is mandatory")
	@JsonProperty("username")
    private String username;
	
	@NotBlank(message = "password is mandatory")
	@JsonProperty("password")
    private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Login [username=" + username + ", password=" + password + "]";
	}


}

