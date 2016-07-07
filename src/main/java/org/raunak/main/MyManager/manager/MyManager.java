package org.raunak.main.MyManager.manager;

import java.util.Collection;

import org.raunak.main.core.Exceptions.DataNotFoundException;
import org.raunak.main.core.Exceptions.InvalidRequestPayloadException;
import org.raunak.main.persistence.Pojo.UserDTO;
import org.raunak.main.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class MyManager {

	@Autowired
   private 	MyService service;

	public ResponseEntity<UserDTO> addUser(UserDTO userDTO) throws InvalidRequestPayloadException, Exception {
		UserDTO createdUser = null;
		createdUser = service.addUser(userDTO);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	public ResponseEntity<Collection<UserDTO>> getAllUsers() {

		return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);

	}

	public ResponseEntity<UserDTO> getUser(String userId) throws InvalidRequestPayloadException, DataNotFoundException {
		UserDTO user = null;
		user = service.getUser(userId);
		return new ResponseEntity<>(user, HttpStatus.FOUND);
	}

	public ResponseEntity<UserDTO> deleteUser(String userId)
			throws InvalidRequestPayloadException, DataNotFoundException {
		service.deleteUser(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	
}
