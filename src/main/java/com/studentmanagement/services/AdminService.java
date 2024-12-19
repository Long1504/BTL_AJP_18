package com.studentmanagement.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmanagement.daos.AdminDAO;
import com.studentmanagement.models.Admin;

@Service
public class AdminService {
	@Autowired
	private AdminDAO adminDAO;

	public Admin getAdminById(String adminId) {
		return adminDAO.getAdminById(adminId);
	}

}
