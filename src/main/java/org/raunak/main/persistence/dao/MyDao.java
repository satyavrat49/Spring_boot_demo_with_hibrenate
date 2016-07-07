package org.raunak.main.persistence.dao;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.raunak.main.core.dao.abstarctDao.MyAbstractDao;
import org.raunak.main.persistence.Entities.RoleEntity;
import org.raunak.main.persistence.Entities.UserEntity;
import org.raunak.main.persistence.Pojo.RoleDTO;
import org.raunak.main.persistence.Pojo.UserDTO;
import org.raunak.main.utill.MyStringUtill;
import org.springframework.stereotype.Repository;

@Repository
public class MyDao extends MyAbstractDao<UserEntity> {

	public MyDao() {
		super(UserEntity.class);
	}

	public UserDTO saveUser(UserDTO userDetails) {
		UserEntity userEntity = mapUserDTOToEntity(userDetails);
		Session session = getSession();
		String userId = (String) session.save(userEntity);
		userDetails.setUserId(userId);
		return userDetails;
	}
	public UserEntity mapUserDTOToEntity(UserDTO userDTO) {
		if (MyStringUtill.isNull(userDTO)) {
			return null;
		}
		
		UserEntity userEntity = new UserEntity();
		userEntity.setState(userDTO.getState());
		userEntity.setEmail(userDTO.getEmail());
		userEntity.setPassword(userDTO.getPassword());
		userEntity.setUsername(userDTO.getUsername());
		userEntity.setFirstname(userDTO.getFirstname());
		userEntity.setLastname(userDTO.getLastname());
		userEntity.setToken(userDTO.getToken());
		userEntity.setRole(getRoleEntity(userDTO.getRole().getRoleId()));
		return userEntity;
	}
	public RoleEntity getRoleEntity(String roleId) {
		Session session = getSession();
		RoleEntity roleEntity = (RoleEntity) session.get(RoleEntity.class, roleId);
		return roleEntity;
	}

	public List<UserDTO> getAllUsers() {
		Session session = getSession();
		List<UserEntity> users = (List<UserEntity>) session.createQuery("from UserEntity").list();
		List<UserDTO> userDTOs = new LinkedList<>();
		for (UserEntity userEntity : users) {
			UserDTO userDTO = mapUserEntityToDTO(userEntity);
			userDTOs.add(userDTO);
		}
		return userDTOs;
	}
	public UserDTO mapUserEntityToDTO(UserEntity userEntity) {
		if (MyStringUtill.isNull(userEntity)) {
			return null;
		}
		UserDTO userDTO = new UserDTO();
		if (!MyStringUtill.isNullOrEmpty(userEntity.getEmailVerificationToken())) {
			userDTO.setEmailVerificationToken(userEntity.getEmailVerificationToken());
		}

		userDTO.setUserId(userEntity.getUserId());
		userDTO.setEmail(userEntity.getEmail());
		userDTO.setPassword(userEntity.getPassword());
		userDTO.setUsername(userEntity.getUsername());
		userDTO.setFirstname(userEntity.getFirstname());
		userDTO.setLastname(userEntity.getLastname());
		userDTO.setToken(userEntity.getToken());
		userDTO.setState(userEntity.getState());
		RoleEntity roleEntity = userEntity.getRole();
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setRole(roleEntity.getRole());
		roleDTO.setRoleId(roleEntity.getRoleId());
		userDTO.setRole(roleDTO);
		return userDTO;
	}

	public UserDTO getUser(String userId) {
		Session session = getSession();
		UserEntity userEntity = (UserEntity) session.get(UserEntity.class, userId);
		UserDTO user = mapUserEntityToDTO(userEntity);
		return user;
	}

	public boolean deleteUser(String userId) {
		Session session = getSession();
		Query query = session.createQuery("DELETE FROM UserEntity WHERE userId = :userId");
		query.setParameter("userId", userId);
		int deletedRecords = query.executeUpdate();
		boolean deleted = false;
		if (deletedRecords > 0) {
			deleted = true;
		}
		return deleted;
	}

}
