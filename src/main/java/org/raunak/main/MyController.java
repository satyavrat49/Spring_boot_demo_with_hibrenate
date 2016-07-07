package org.raunak.main;


import java.util.Collection;

import org.raunak.main.MyManager.manager.MyManager;
import org.raunak.main.core.Exceptions.DataNotFoundException;
import org.raunak.main.core.Exceptions.InvalidRequestPayloadException;
import org.raunak.main.persistence.Pojo.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class MyController {
	
	@Autowired 
	private MyManager manager;

	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO)
			throws InvalidRequestPayloadException, Exception {
		ResponseEntity<UserDTO> user = manager.addUser(userDTO);

		return user;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<UserDTO>> getUsers() {
		ResponseEntity<Collection<UserDTO>> response = null;
		response = manager.getAllUsers();

		return response;

	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> getUser(@PathVariable("id") String userId)
			throws InvalidRequestPayloadException, DataNotFoundException {
		return manager.getUser(userId);
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") String userId)
			throws InvalidRequestPayloadException, DataNotFoundException {
		return manager.deleteUser(userId);
	}
}
