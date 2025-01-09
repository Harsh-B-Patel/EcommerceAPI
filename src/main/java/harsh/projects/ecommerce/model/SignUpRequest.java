package harsh.projects.ecommerce.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SignUpRequest {
	
	
	@Valid
	@JsonProperty("credentials")
    private Login credentials;
	
	@Valid
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

	@Override
	public int hashCode() {
		return Objects.hash(credentials, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SignUpRequest other = (SignUpRequest) obj;
		return Objects.equals(credentials, other.credentials) && Objects.equals(user, other.user);
	}
    
}

