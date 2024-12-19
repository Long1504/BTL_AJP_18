package com.studentmanagement.models;

import java.sql.Date;

public class TeacherSchedule {
	private String courseName;
	private String courseId;
	private String date;
	private String time;
	private String room;

	public TeacherSchedule() {
	}

	public TeacherSchedule(String courseName, String courseId, String date, String time, String room) {
		this.courseName = courseName;
		this.courseId = courseId;
		this.date = date;
		this.time = time;
		this.room = room;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	@Override
	public String toString() {
		return "TeacherSchedule [courseName=" + courseName + ", courseId=" + courseId + ", date=" + date + ", time="
				+ time + ", room=" + room + "]";
	}

}
