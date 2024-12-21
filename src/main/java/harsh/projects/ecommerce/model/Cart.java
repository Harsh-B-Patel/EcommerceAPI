package harsh.projects.ecommerce.model;

import java.util.List;

public class Cart {
    private CartDetails cart;

    // Getters and Setters
    public CartDetails getCart() {
        return cart;
    }

    public void setCart(CartDetails cart) {
        this.cart = cart;
    }

    public static class CartDetails {
        private Integer id;
        private String username;
        private List<CartItem> cartItems;

        // Getters and Setters
        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
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

    public static class CartItem {
        private Product product;
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
}
