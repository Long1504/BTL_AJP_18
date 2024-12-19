package com.studentmanagement.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmanagement.daos.TeacherDAO;
import com.studentmanagement.models.Course;
import com.studentmanagement.models.Teacher;
import com.studentmanagement.models.TeacherSchedule;

@org.springframework.transaction.annotation.Transactional
@Service
public class TeacherService {
	@Autowired
	private TeacherDAO teacherDAO;
	
	public ArrayList<Teacher> getAllTeacher() {
		return teacherDAO.getAllTeacher();
	}

	public Teacher getTeacherById(String teacherId) {
		return teacherDAO.getTeacherById(teacherId);
	}

	public int updateTeacher(Teacher teacher) {
		return teacherDAO.updateTeacher(teacher);
	}
	
	public ArrayList<Course> getCourseByTeacher(String teacherId) {
		return teacherDAO.getCourseByTeacher(teacherId);
	}
	
	public ArrayList<TeacherSchedule> getScheduleByTeacher(String teacherId) {
		return teacherDAO.getScheduleByTeacher(teacherId);
	}
	
	public ArrayList<Teacher> getTeacherByDepartmentName(String departmentName) {
		if(departmentName.equals("all")) {
			return teacherDAO.getAllTeacher();
		}
		else {
			return teacherDAO.getTeacherByDepartmentName(departmentName);
		}
	}
	
	public void deleteTeacherById(String teacherId) {
		teacherDAO.deleteTeacherById(teacherId);
	}
	
	public void addTeacher(Teacher teacher) {
		teacherDAO.addTeacher(teacher);
	}
}
