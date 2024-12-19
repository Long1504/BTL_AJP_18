package com.studentmanagement.models;

public class Class {
	private String classId;
	private String name;
	private String departmentId;

	public Class() {
	}

	public Class(String classId, String name, String departmentId) {
		this.classId = classId;
		this.name = name;
		this.departmentId = departmentId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public String toString() {
		return "Class [classId=" + classId + ", name=" + name + ", departmentId=" + departmentId + "]";
	}

}
