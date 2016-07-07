package org.raunak.main.service;

import java.util.Collection;

import org.raunak.main.core.Exceptions.DataNotFoundException;
import org.raunak.main.core.Exceptions.InvalidRequestPayloadException;
import org.raunak.main.persistence.Pojo.UserDTO;


public interface MyService {
	UserDTO addUser(UserDTO userDTO) throws InvalidRequestPayloadException, Exception;

	Collection<UserDTO> getAllUsers();

	UserDTO getUser(String userId) throws DataNotFoundException, InvalidRequestPayloadException;

	boolean deleteUser(String userId) throws DataNotFoundException, InvalidRequestPayloadException;


}
