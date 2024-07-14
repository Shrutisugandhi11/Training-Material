package com.epam.model;


import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
@Component
public class User {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int  userId;
	@Column
	protected String userName;
	@Column
	protected String password;
	@Column
	protected String roleType="user";

	public User() {

	}

	public User(String userName, String password, String roleType) {
		super();
		this.userName = userName;
		this.password = password;
		this.roleType = roleType;
	}

	public User(String username, String password) {
		this.userName = username;
		this.password = password;
	}

	public User(int userId, String userName, String password, String roleType) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.roleType = roleType;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(password, roleType, userName);
			}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(password, other.password) && Objects.equals(roleType, other.roleType)
				&& Objects.equals(userName, other.userName);
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", roleType=" + roleType + "]";
	}

}
