package harsh.projects.ecommerce.advice;
import harsh.projects.ecommerce.exception.UserAlreadyExistsException;
import harsh.projects.ecommerce.exception.UserDoesNotExistsException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class AppExceptionHandler {

	/**
	 * Exception response handling of 400 BadRequest MethodArgumentNotValidException
	 * @param ex
	 * @return
	 */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleInvalidArgument(MethodArgumentNotValidException ex) {
    	
        Map<String, Object> errorMap = new HashMap<>();
        Map<String, String> map = new HashMap<>();
        
        // Add all the error messages to error Response
        ex.getBindingResult().getFieldErrors().forEach(error -> {	
            map.put(error.getField(), error.getDefaultMessage());

        });
        errorMap.put("errorMessage ", map);
        return errorMap;
    }

	/**
	 * Exception response handling of 500 InternalServerError UserAlreadyExistsException
	 * @param ex
	 * @return
	 */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public Map<String, String> UserAlreadyExistsExceptionResponse(UserAlreadyExistsException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", ex.getMessage());
        return errorMap;
    }
    
	/**
	 * Exception response handling of 500 InternalServerError UserDoesNotExistsException
	 * @param ex
	 * @return
	 */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserDoesNotExistsException.class)
    public Map<String, String> UserDoesNotExistsExceptionResponse(UserDoesNotExistsException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", ex.getMessage());
        return errorMap;
    }

}