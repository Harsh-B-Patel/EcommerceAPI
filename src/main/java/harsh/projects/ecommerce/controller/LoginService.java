package harsh.projects.ecommerce.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

import ch.qos.logback.classic.Logger;
import harsh.projects.ecommerce.DAO.DaoUtil;
import harsh.projects.ecommerce.exception.UserAlreadyExistsException;
import harsh.projects.ecommerce.exception.UserDoesNotExistsException;
import harsh.projects.ecommerce.model.Login;
import harsh.projects.ecommerce.model.LoginResponse;
import harsh.projects.ecommerce.model.SignUpRequest;
import harsh.projects.ecommerce.model.Token;
import harsh.projects.ecommerce.model.User;
import harsh.projects.ecommerce.service.Constants;
import harsh.projects.ecommerce.service.JwtUtil;
import jakarta.validation.Payload;
import jakarta.validation.Valid;

@RestController
@RequestMapping("")
public class LoginService {

	@PostMapping("/login")
	public LoginResponse loginMethod(@Valid @RequestBody Login loginInfo) throws UserDoesNotExistsException {

		System.out.println(loginInfo.toString());

		// go to db and find the user
		Boolean user_exists = DaoUtil.checkLogin(loginInfo.getUsername());
		System.out.println("LoginService.loginMethod.user_exist:" + user_exists);

		if (user_exists) {

			// get user info from DB
			User user = DaoUtil.getUserDetails(loginInfo.getUsername());

			// get new token
			Token token = new Token();
			token.setToken(JwtUtil.generateToken(Constants.SUBJECT, user.getUsername()));

			return new LoginResponse(token, user);

		} else {
			// user does not exists return custom error
			throw new UserDoesNotExistsException("User: " + loginInfo.getUsername() + " does not exists");

		}
	}

	@PostMapping("/signup")
	public LoginResponse signUpMethod(@Valid @RequestBody SignUpRequest signUpInfo) throws UserAlreadyExistsException {

		// check if username exists
		System.out.println("LoginService.signUpMethod : Checking if Username Exists");
		boolean user_exists = DaoUtil.checkLogin(signUpInfo.getCredentials().getUsername());

		if (!user_exists) {
			System.out.println("LoginService.signUpMethod :  Username does not Exists");
			// go to db and add the user
			User returnUserInfo = DaoUtil.setUserDetails(signUpInfo.getCredentials(), signUpInfo.getUser());

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
