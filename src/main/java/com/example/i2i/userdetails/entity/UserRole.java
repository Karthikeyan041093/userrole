package com.example.i2i.userdetails.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.example.i2i.userdetails.dto.Auditable;

@Entity
@Table(name = "userrole")
@Audited
public class UserRole extends Auditable<String> implements java.io.Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pk_user_role_id", unique = true, nullable = false)
	private int userRoleId;

	@Column(name = "role_name")
	private String roleName;

	// @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	// @JoinColumn(name = "pk_user_id", referencedColumnName = "pk_user_id")
	// @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	// @JoinColumn(name = "pk_user_id", insertable = false, updatable = false,
	// nullable = false, unique = true)
	// @JsonIgnore
	// private User user;

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
