package harsh.projects.ecommerce.model;

public class LoginResponse {
    private Token token;
    private User user;
    
    public LoginResponse(Token token , User user){
    	this.token = token; 
    	this.user = user;
    }
    
    // Getters and Setters
    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
