package harsh.projects.ecommerce.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import harsh.projects.ecommerce.DAO.UserDaoUtil;
import harsh.projects.ecommerce.exception.TokenInValidException;
import harsh.projects.ecommerce.exception.UserDoesNotExistsException;
import harsh.projects.ecommerce.model.User;
import harsh.projects.ecommerce.service.JwtUtil;
import harsh.projects.ecommerce.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	
	@GetMapping("/{userId}")
	public User GetUserByUserID(@Valid @PathVariable("userId") int userId, @RequestHeader ("token") String token) throws TokenInValidException, UserDoesNotExistsException {
		return UserService.GetUserByUserID(userId, token);
		
	}
	
	@PostMapping("/{userId}")
	public User PostUserByUserID(@Valid @PathVariable("userId") int userId, @RequestHeader ("token") String token, @RequestBody User user) throws TokenInValidException, UserDoesNotExistsException {
		return UserService.PostUserByUserID(userId, token, user);
		
	}
	
	@DeleteMapping("/{userId}")
	public String DeleteUserByUserID(@Valid @PathVariable("userId") int userId, @RequestHeader ("token") String token) throws TokenInValidException, UserDoesNotExistsException {
		return UserService.DeleteUserByUserID(userId, token);
		
	}
}
