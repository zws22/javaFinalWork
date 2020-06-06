package org.zws.domain;

public class User {
	private String id;
	private String name;
	private String sex;
	private String userPwd;
	private String role;
	public User() {
	}
	public User(String id, String name, String sex, String userPwd, String role) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.userPwd = userPwd;
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", sex=" + sex + ", userPwd=" + userPwd + ", role=" + role + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
