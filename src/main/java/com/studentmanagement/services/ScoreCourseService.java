package com.studentmanagement.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmanagement.daos.ScoreCourseDAO;
import com.studentmanagement.models.ScoreCourse;

@Service
public class ScoreCourseService {
	@Autowired
	private ScoreCourseDAO scoreCourseDAO;
	
	public ArrayList<ScoreCourse> getAllScoreCourseByStudentId(String studentId) {
		return scoreCourseDAO.getAllScoreCourseByStudentId(studentId);
	}
	
	public ArrayList<ScoreCourse> getScoreCourseByCourseId(String courseId) {
		return scoreCourseDAO.getScoreCourseByCourseId(courseId);
	}

	public int updateScoreForStudent(ScoreCourse scoreCourse) {
		return scoreCourseDAO.updateScoreForStudent(scoreCourse);
	}
	
	public double getGPAByStudentId(String studentId) {
		return scoreCourseDAO.getGPAByStudentId(studentId);
	}
}
