package harsh.projects.ecommerce.service;

import org.springframework.web.bind.annotation.RequestBody;

import harsh.projects.ecommerce.DAO.UserDaoUtil;
import harsh.projects.ecommerce.exception.UserDoesNotExistsException;
import harsh.projects.ecommerce.model.Login;
import harsh.projects.ecommerce.model.LoginResponse;
import harsh.projects.ecommerce.model.Token;
import harsh.projects.ecommerce.model.User;
import jakarta.validation.Valid;

public class LoginService {

	public static LoginResponse Login(Login loginInfo) throws UserDoesNotExistsException {

		System.out.println("Enter LoginService.loginMethod");
		// go to db and find the user
		Boolean user_exists = UserDaoUtil.checkUserByUsername(loginInfo.getUsername());
		System.out.println("LoginService.loginMethod.user_exist: " + user_exists);

		if (user_exists) {

			// check if credentials are correct
			boolean validCredentials = UserDaoUtil.verifyLoginCredentials(loginInfo);
			if (validCredentials) {
				// get user info from DB
				User user = UserDaoUtil.getUserDetails(loginInfo.getUsername());

				// get new token
				Token token = new Token();
				token.setToken(JwtUtil.generateToken(Constants.SUBJECT, user.getUsername()));

				return new LoginResponse(token, user);
			} else {
				throw new UserDoesNotExistsException("User: " + loginInfo.getUsername() + " does not exists");
			}

		} else {
			// user does not exists return custom error
			throw new UserDoesNotExistsException("User: " + loginInfo.getUsername() + " does not exists");

		}
	}
}
