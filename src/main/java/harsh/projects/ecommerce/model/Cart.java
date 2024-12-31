package harsh.projects.ecommerce.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@JsonInclude(Include.NON_NULL) // Ignore null fields 
@JsonIgnoreProperties(ignoreUnknown = true) //ignore unknown fields 
public class Cart {
		
		
		@NotBlank(message = "UserId is mandatory")
        private Integer userid;
		
        private String username;
        
        @Valid
        @NotBlank(message = "CartItems are mandatory")
        private List<CartItem> cartItems;

        // Getters and Setters
        public Integer getId() {
            return userid;
        }

        public void setId(Integer id) {
            this.userid = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public List<CartItem> getCartItems() {
            return cartItems;
        }

        public void setCartItems(List<CartItem> cartItems) {
            this.cartItems = cartItems;
        }
    }

  
