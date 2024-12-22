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
import harsh.projects.ecommerce.service.LoginService;
import harsh.projects.ecommerce.service.SignUpService;
import jakarta.validation.Payload;
import jakarta.validation.Valid;

@RestController
@RequestMapping("")
public class LoginController {

	@PostMapping("/login")
	public LoginResponse loginMethod(@Valid @RequestBody Login loginInfo) throws UserDoesNotExistsException {
		return LoginService.Login(loginInfo);
	}



}
