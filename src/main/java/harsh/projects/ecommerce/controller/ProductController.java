package harsh.projects.ecommerce.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import harsh.projects.ecommerce.exception.TokenInValidException;
import harsh.projects.ecommerce.exception.UserDoesNotExistsException;
import harsh.projects.ecommerce.model.Product;
import harsh.projects.ecommerce.model.User;
import harsh.projects.ecommerce.service.ProductService;
import harsh.projects.ecommerce.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {

	
	@GetMapping("/{productId}")
	public Product GetProductByID(@Valid @PathVariable("productId") int Id, @RequestHeader ("token") String token) throws TokenInValidException, UserDoesNotExistsException {
		return ProductService.GetProductByID(Id, token);
		
	}
	
	@PostMapping("/{productId}")
	@PutMapping("/{productId}")
	public Product PostProductByID(@Valid @PathVariable("productId") int Id, @RequestHeader ("token") String token, @RequestBody Product product) throws TokenInValidException, UserDoesNotExistsException {
		return ProductService.UpdateProduct(Id, product, token);
		
	}
	
	@DeleteMapping("/{productId}")
	public String DeleteProductByID(@Valid @PathVariable("productId") int userId, @RequestHeader ("token") String token) throws TokenInValidException, UserDoesNotExistsException {
		return ProductService.DeleteProduct(userId, token);
		
	}
}