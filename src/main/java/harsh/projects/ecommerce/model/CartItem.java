package harsh.projects.ecommerce.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public class CartItem {
	
	@Valid
    private Product product;
    
	
	@NotBlank(message = "Quantity is mandatory")
	private Integer quantity;

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

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

