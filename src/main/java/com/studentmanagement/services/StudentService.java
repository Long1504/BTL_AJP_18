package com.studentmanagement.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmanagement.daos.StudentDAO;
import com.studentmanagement.models.Student;
import com.studentmanagement.models.StudentSchedule;

@Service
public class StudentService {
	@Autowired
	private StudentDAO studentDAO;
	
	public ArrayList<Student> getAllStudent() {
		return studentDAO.getAllStudent();
	}
	
	public Student getStudentById(String studentId) {
		return studentDAO.getStudentById(studentId);
	}
	
	public ArrayList<Student> getStudentByClassIdAndDepartmentId(String classId, String departmentId) {
		if(classId.equals("all") && departmentId.equals("all")) {
			return studentDAO.getAllStudent();
		}
		else if(classId.equals("all")) {
			return studentDAO.getStudentByDepartmentId(departmentId);
		}
		else if(departmentId.equals("all")) {
			return studentDAO.getStudentByClassId(classId);
		}
		else {
			return studentDAO.getStudentByClassIdAndDepartmentId(classId, departmentId);
		}
	}
	
	public void updateStudent(Student student) {
		studentDAO.updateStudent(student);
	}
	
	public void deleteStudentById(String studentId) {
		studentDAO.deleteStudentById(studentId);
	}
	
	public void addStudent(Student student) {
		studentDAO.addStudent(student);
	}
	
	public ArrayList<StudentSchedule> getScheduleByStudentId(String studentId) {
		return studentDAO.getScheduleByStudentId(studentId);
	}
}
