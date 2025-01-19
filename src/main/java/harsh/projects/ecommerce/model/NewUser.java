package harsh.projects.ecommerce.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonInclude(Include.NON_NULL) // Ignore null fields 
@JsonIgnoreProperties(ignoreUnknown = true) //ignore unknown fields 
public class NewUser{
	
	@NotBlank(message = "firstName is mandatory")
    private String firstName;
	
	@NotBlank(message = "lastName is mandatory")
    private String lastName;
    
    private String email;
    private String phone;

    // Getters and Setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

	@Override
	public String toString() {
		return "NewUser [firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", phone=" + phone + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, lastName, phone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewUser other = (NewUser) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(phone, other.phone);
	}
    
    
}

