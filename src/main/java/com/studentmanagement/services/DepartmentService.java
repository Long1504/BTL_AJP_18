package com.studentmanagement.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmanagement.daos.DepartmentDAO;
import com.studentmanagement.models.Department;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentDAO departmentDAO;
	
	public ArrayList<Department> getAllDepartment() {
		return departmentDAO.getAllDepartment();
	}
}
