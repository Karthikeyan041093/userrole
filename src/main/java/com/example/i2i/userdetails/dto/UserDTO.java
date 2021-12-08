package com.example.i2i.userdetails.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int userId;

	private String userName;
	private String userPassword;
	private List<UserRoleDTO> userRoleList = new ArrayList<>();

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<UserRoleDTO> getUserRoleList() {
		return userRoleList;
	}

	public void setUserRoleList(List<UserRoleDTO> userRoleList) {
		this.userRoleList = userRoleList;
	}

}
