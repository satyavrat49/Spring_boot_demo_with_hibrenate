package org.raunak.main.persistence.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ROLE_MST")
public class RoleEntity {

	@Id
	@GeneratedValue
	@Column(name = "ROLE_ID")
	private String roleId;
	@Column(name = "ROLE")
	private String role;
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "RoleEntity [roleId=" + roleId + ", role=" + role + "]";
	}
	
}
