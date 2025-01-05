package harsh.projects.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@JsonInclude(Include.NON_NULL) // Ignore null fields 
@JsonIgnoreProperties(ignoreUnknown = true) //ignore unknown fields 
public class Cart {
		
		
		@NotBlank(message = "UserId is mandatory")
        private int userId;
        
        @Valid
        @NotBlank(message = "CartItems are mandatory")
        private List<CartItem> cartItems = new ArrayList<CartItem>();

        // Getters and Setters

        public int getUserId() {
			return userId;
		}

		public void setUserId(int userid) {
			this.userId = userid;
		}
		
        public List<CartItem> getCartItems() {
            return cartItems;
        }

		public void setCartItems(List<CartItem> cartItems) {
            this.cartItems = cartItems;
        }
    }

  
