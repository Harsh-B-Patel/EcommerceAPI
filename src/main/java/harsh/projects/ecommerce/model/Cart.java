package harsh.projects.ecommerce.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

		@Override
		public int hashCode() {
			return Objects.hash(cartItems, userId);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Cart other = (Cart) obj;
			return Objects.equals(cartItems, other.cartItems) && userId == other.userId;
		}


		
    }

  
