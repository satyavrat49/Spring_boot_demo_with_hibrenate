package org.raunak.main.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Collection;

import org.raunak.main.core.Exceptions.DataNotFoundException;
import org.raunak.main.core.Exceptions.InvalidRequestPayloadException;
import org.raunak.main.persistence.Pojo.RoleDTO;
import org.raunak.main.persistence.Pojo.UserDTO;
import org.raunak.main.persistence.dao.MyDao;
import org.raunak.main.utill.MyStringUtill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl implements MyService {
	@Autowired
	private MyDao myDao;

	@Override
	public UserDTO addUser(UserDTO userDTO) throws InvalidRequestPayloadException, Exception {
		if (MyStringUtill.isNull(userDTO) || MyStringUtill.isNullOrEmpty(userDTO.getUsername())

				|| MyStringUtill.isNullOrEmpty(userDTO.getFirstname())) {
			throw new InvalidRequestPayloadException();
		}
		String input = userDTO.getUsername() + userDTO.getPassword();
		RoleDTO role = new RoleDTO();
		role.setRoleId("3");
		role.setRole("USER");
		userDTO.setState("DACTIVE");
		userDTO.setRole(role);
		if (MyStringUtill.isNullOrEmpty(userDTO.getPassword())) {
			userDTO.setPassword("Login1-2");
		}
		byte[] inputBytes = input.getBytes();
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(inputBytes);
		String token = String.format("%032x", new BigInteger(1, md5.digest()));
		userDTO.setToken(token);
		myDao.saveUser(userDTO);
		return userDTO;
	}

	@Override
	public Collection<UserDTO> getAllUsers() {
		return myDao.getAllUsers();
	}
	@Override
	public UserDTO getUser(String userId) throws DataNotFoundException, InvalidRequestPayloadException {
		if (MyStringUtill.isNullOrEmpty(userId)) {
			throw new InvalidRequestPayloadException();
		}
		UserDTO userDTO = myDao.getUser(userId);
		if (MyStringUtill.isNull(userDTO)) {
			throw new DataNotFoundException();
		}
		return userDTO;
	}
	@Override
	public boolean deleteUser(String userId) throws DataNotFoundException, InvalidRequestPayloadException {
		if (MyStringUtill.isNullOrEmpty(userId)) {
			throw new InvalidRequestPayloadException();
		}
		UserDTO userDTO = myDao.getUser(userId);
		if (MyStringUtill.isNull(userDTO)) {
			throw new DataNotFoundException();
		}
		boolean deleted = myDao.deleteUser(userId);
		return deleted;
	}

}
