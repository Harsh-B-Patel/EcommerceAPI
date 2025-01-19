package harsh.projects.ecommerce.model;

import java.util.Objects;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public class CartItem {
	
	@Valid
    private Product product;
    
	
	@NotBlank(message = "Quantity is mandatory")
	private int quantity;

    // Getters and Setters
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

	@Override
	public int hashCode() {
		return Objects.hash(product, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartItem other = (CartItem) obj;
		return Objects.equals(product, other.product) && quantity == other.quantity;
	}
    
    
}

