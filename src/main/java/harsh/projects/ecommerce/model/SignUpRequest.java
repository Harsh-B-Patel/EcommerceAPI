package harsh.projects.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//@JsonInclude(Include.NON_NULL) // Ignore null fields 
//@JsonIgnoreProperties(ignoreUnknown = true) //ignore unknown fields 
public class SignUpRequest {
	
	//@NotBlank(message = "crentials is mandatory")
	@Valid
	@NotNull(message = "credentials cannot be Null")
	@JsonProperty("credentials")
    private Login credentials;
	
	@Valid
	@NotNull(message = "user cannot be Null")
	@JsonProperty("user")
    private NewUser user;

    // Getters and Setters
    public Login getCredentials() {
        return credentials;
    }

    public void setCredentials(Login credentials) {
        this.credentials = credentials;
    }

    public NewUser getUser() {
        return user;
    }

    public void setUser(NewUser user) {
        this.user = user;
    }

	@Override
	public String toString() {
		return "SignUpRequest [credentials=" + credentials + ", user=" + user + "]";
	}
    
}

