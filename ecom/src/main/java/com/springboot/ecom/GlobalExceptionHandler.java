package com.springboot.ecom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.springboot.ecom.dto.ResponseMessageDto;
import com.springboot.ecom.exception.InvalidUsernameException;
import com.springboot.ecom.exception.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@Autowired
	private ResponseMessageDto dto; 
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(Exception e){
		 dto.setMsg(e.getMessage());
		 return ResponseEntity.badRequest().body(dto);
	}
	
	@ExceptionHandler(InvalidUsernameException.class)
	
	public ResponseEntity<?> handleInvalidUsernameException(Exception e){
		 dto.setMsg(e.getMessage());
		 return ResponseEntity.badRequest().body(dto);
	}
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception e) {
        dto.setMsg("An error occurred: " + e.getMessage());
        return ResponseEntity.badRequest().body(dto);
    }
}

