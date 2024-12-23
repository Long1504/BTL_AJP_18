package com.studentmanagement.models;

public class Admin {
	private String adminId;
	private String name;
	private String password;
	private String role;

	public Admin() {
	}

	public Admin(String adminId, String name, String password, String role) {
		this.adminId = adminId;
		this.name = name;
		this.password = password;
		this.role = role;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", name=" + name + ", password=" + password + ", role=" + role + "]";
	}

}
