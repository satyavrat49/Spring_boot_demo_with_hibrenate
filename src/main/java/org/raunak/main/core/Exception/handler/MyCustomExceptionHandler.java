package org.raunak.main.core.Exception.handler;

import org.apache.log4j.Logger;
import org.raunak.main.core.Exceptions.DataNotFoundException;
import org.raunak.main.core.Exceptions.InvalidRequestPayloadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class MyCustomExceptionHandler {
private static Logger logger = Logger.getLogger(MyCustomExceptionHandler.class);
	
	@ExceptionHandler(value = DataNotFoundException.class)
	public ResponseEntity<String> handleDataNotFoundException(DataNotFoundException e){
		logger.error(e.getMessage(), e);
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = InvalidRequestPayloadException.class)
	public ResponseEntity<String> handleInvalidRequestPayloadException(InvalidRequestPayloadException e){
		logger.error(e.getMessage(), e);
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String> handleGenericException(Exception e){
		logger.error(e.getMessage(), e);
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
