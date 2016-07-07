package org.raunak.main.persistence.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="USER_MST")
public class UserEntity {

	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "USER_ID")
	private String userId;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "USERNAME")
	private String username;
	@Column(name = "FIRST_NAME")
	private String firstname;
	@Column(name = "LAST_NAME")
	private String lastname;
	@Column(name = "PASSWORD")
	private String password;
	@Column(name ="EMAIL_VERIFICATION_TOKEN")
	private String emailVerificationToken;
	@Column(name ="TOKEN")
	private String token;
	
	@Column(name ="USER_STATE")
	private String state;
	
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	@ManyToOne
	@JoinColumn(name = "ROLE_ID")
	private RoleEntity role;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}
	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public RoleEntity getRole() {
		return role;
	}
	public void setRole(RoleEntity role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "UserEntity [userId=" + userId + ", email=" + email + ", username=" + username + ", firstname="
				+ firstname + ", lastname=" + lastname + ", password=" + password + ", emailVerificationToken="
				+ emailVerificationToken + ", token=" + token + ", role=" + role + "]";
	}
	
}
