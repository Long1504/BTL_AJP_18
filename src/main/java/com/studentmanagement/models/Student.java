package com.studentmanagement.models;

import java.sql.Date;

public class Student {
	private String studentId;
	private String name;
	private String gender;
	private Date birthday;
	private String hometown;
	private String phoneNumber;
	private String email;
	private String password;
	private String role;
	private String classId;
	private String departmentId;

	public Student() {
	}

	public Student(String studentId, String name, String gender, Date birthday, String hometown, String phoneNumber,
			String email, String password, String role, String classId, String departmentId) {
		this.studentId = studentId;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.hometown = hometown;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
		this.role = role;
		this.classId = classId;
		this.departmentId = departmentId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", name=" + name + ", gender=" + gender + ", birthday=" + birthday
				+ ", hometown=" + hometown + ", phoneNumber=" + phoneNumber + ", email=" + email + ", password="
				+ password + ", role=" + role + ", classId=" + classId + ", departmentId=" + departmentId + "]";
	}

}
