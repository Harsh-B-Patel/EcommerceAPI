package harsh.projects.ecommerce.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
    	
        Map<String, Object> errorMap = new LinkedHashMap<>();
        errorMap.put("timestamp", LocalDateTime.now());
        errorMap.put("status", "400");
        errorMap.put("error", "Bad Request");
       
        Map<String, String> map = new HashMap<>(); 
        // Add all the error messages to error Response
        ex.getBindingResult().getFieldErrors().forEach(error -> {	
            map.put(error.getField(), error.getDefaultMessage());

        });
        
        errorMap.put("message ", map);
        return errorMap;
    }
    
    /**
     * Exception response for handling invalid token
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TokenInValidException.class)
    public Map<String, Object> handleInvalidToken(TokenInValidException ex) {
        Map<String, Object> errorMap = new LinkedHashMap<>();
        errorMap.put("timestamp", LocalDateTime.now());
        errorMap.put("status", "400");
        errorMap.put("error", "Bad Request");
        errorMap.put("message", ex.getMessage());
        return errorMap;
    }

	/**
	 * Exception response handling of 500 InternalServerError UserAlreadyExistsException
	 * @param ex
	 * @return
	 */
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public Map<String, Object> UserAlreadyExistsExceptionResponse(UserAlreadyExistsException ex) {
        Map<String, Object> errorMap = new LinkedHashMap<>();
        errorMap.put("timestamp", LocalDateTime.now());
        errorMap.put("status", "422");
        errorMap.put("error", "Unprocessable Entity");
        errorMap.put("message", ex.getMessage());
        return errorMap;
    }
    
	/**
	 * Exception response handling of 500 InternalServerError UserDoesNotExistsException
	 * @param ex
	 * @return
	 */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserDoesNotExistsException.class)
    public Map<String, Object> UserDoesNotExistsExceptionResponse(UserDoesNotExistsException ex) {
        Map<String, Object> errorMap = new LinkedHashMap<>();
        errorMap.put("timestamp", LocalDateTime.now());
        errorMap.put("status", "404");
        errorMap.put("error", "Not Found");
        errorMap.put("message", ex.getMessage());
        return errorMap;
    }

}