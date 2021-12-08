package com.example.i2i.userdetails.dto;

public class UserRoleDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int userRoleId;
	private String roleName;

	public int getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
