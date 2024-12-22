package harsh.projects.ecommerce.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import harsh.projects.ecommerce.exception.UserAlreadyExistsException;
import harsh.projects.ecommerce.model.LoginResponse;
import harsh.projects.ecommerce.model.SignUpRequest;
import harsh.projects.ecommerce.service.SignUpService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("")
public class SignUpController {

	@PostMapping("/signup")
	public LoginResponse signUpMethod(@Valid @RequestBody SignUpRequest signUpInfo) throws UserAlreadyExistsException {
		return SignUpService.signUp(signUpInfo);
	}
}
