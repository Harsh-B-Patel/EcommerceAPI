package harsh.projects.ecommerce.service;

import org.springframework.web.bind.annotation.RequestBody;

import harsh.projects.ecommerce.DAO.UserDaoUtil;
import harsh.projects.ecommerce.exception.UserAlreadyExistsException;
import harsh.projects.ecommerce.model.LoginResponse;
import harsh.projects.ecommerce.model.SignUpRequest;
import harsh.projects.ecommerce.model.Token;
import harsh.projects.ecommerce.model.User;
import jakarta.validation.Valid;

public class SignUpService {

	public static LoginResponse signUp(SignUpRequest signUpInfo) throws UserAlreadyExistsException {

		// check if username exists
		System.out.println("LoginService.signUpMethod : Checking if Username Exists");
		boolean user_exists = UserDaoUtil.checkUserByUsername(signUpInfo.getCredentials().getUsername());

		if (!user_exists) {
			System.out.println("LoginService.signUpMethod :  Username does not Exists");
			// go to db and add the user
			User returnUserInfo = UserDaoUtil.setUserDetails(signUpInfo.getCredentials(), signUpInfo.getUser());

			// add Jwt to Response object
			Token token = new Token();
			token.setToken(JwtUtil.generateToken(Constants.SUBJECT, returnUserInfo.getUsername()));
			LoginResponse signUpResponse = new LoginResponse(token, returnUserInfo);

			return signUpResponse;

		} else {
			System.out.println("LoginService.signUpMethod :  Username Exists");
			throw new UserAlreadyExistsException(
					"User: " + signUpInfo.getCredentials().getUsername() + " already exists");
		}

	}
	
}
