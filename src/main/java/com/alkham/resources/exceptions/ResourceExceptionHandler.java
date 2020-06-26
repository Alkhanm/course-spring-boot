package com.alkham.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alkham.services.exceptions.ResourceNotFoundException;

@ControllerAdvice //Essa classe está mapeada para interceptar erros e realizar os possiveis tratamentos
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class) // Esse metodo tem agora a capacidade de interceptar requisição que tenha dado esse tipo de erro.
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
}
