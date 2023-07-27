package ru.netology.authservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.ResponseEntity;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

	@ResponseBody
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String userNotFoundHandler(UserNotFoundException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler(InvalidCredentials.class)
	public ResponseEntity<String> icHandler(InvalidCredentials e) {
		return new ResponseEntity<>("Exception in getAuthorities method", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UnauthorizedUser.class)
	public ResponseEntity<String> uuHandler(UnauthorizedUser e) {
		return new ResponseEntity<>("Exception in getAuthorities method", HttpStatus.UNAUTHORIZED);
	}
}
