package com.orderme.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@Entity(name = "User")
@Table(name = "orderme_user")
public class User {

	@Id // signifies the primary key
	@Column(nullable = false, length = 50)
	private String email;

	@Column(nullable = false, length = 50)
	private String password;

	private String name;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int timeLogin;

	@Enumerated(EnumType.STRING)
	private Role role;

	private Date createDate;

	private Date lastSync;

	private boolean isOutOfSync;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTimeLogin() {
		return timeLogin;
	}

	public void setTimeLogin(int timeLogin) {
		this.timeLogin = timeLogin;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@XmlJavaTypeAdapter(DateSerializer.class)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastSync() {
		return lastSync;
	}

	public void setLastSync(Date lastSync) {
		this.lastSync = lastSync;
	}

	public boolean isOutOfSync() {
		return isOutOfSync;
	}

	public void setIsOutOfSync(boolean isOutOfSync) {
		this.isOutOfSync = isOutOfSync;
	}

}
