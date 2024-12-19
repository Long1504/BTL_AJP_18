package com.studentmanagement.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmanagement.daos.CourseDAO;
import com.studentmanagement.models.Course;

@Service
public class CourseService {
	@Autowired
	private CourseDAO courseDAO;
	
	public Course getCourseById(String courseId) {
		return courseDAO.getCourseById(courseId);
	}
	
	public ArrayList<Course> getAllCourse() {
		return courseDAO.getAllCourse();
	}
	
	public void updateCourse(Course course) {
		courseDAO.updateCourse(course);
	}
	
	public void deleteCourseById(String courseId) {
		courseDAO.deleteCourseById(courseId);
	}
	
	public void addCourse(Course course) {
		courseDAO.addCourse(course);
	}
}
