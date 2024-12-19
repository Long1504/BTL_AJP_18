package com.studentmanagement.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmanagement.daos.ClassDAO;
import com.studentmanagement.models.Class;

@Service
public class ClassService {
	@Autowired
	private ClassDAO classDAO;
	
	public ArrayList<Class> getAllClass() {
		return classDAO.getAllClass();
	}
}
