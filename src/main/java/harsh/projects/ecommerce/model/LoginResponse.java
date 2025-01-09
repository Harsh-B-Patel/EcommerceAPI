package harsh.projects.ecommerce.model;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(token, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginResponse other = (LoginResponse) obj;
		return Objects.equals(token, other.token) && Objects.equals(user, other.user);
	}
}
