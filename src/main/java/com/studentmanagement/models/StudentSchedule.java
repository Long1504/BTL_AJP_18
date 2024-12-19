package com.studentmanagement.models;

public class StudentSchedule {
	private String courseName;
	private String courseId;
	private String date;
	private String time;
	private String room;
	private String teacherName;

	public StudentSchedule() {
	}

	public StudentSchedule(String courseName, String courseId, String date, String time, String room,
			String teacherName) {
		this.courseName = courseName;
		this.courseId = courseId;
		this.date = date;
		this.time = time;
		this.room = room;
		this.teacherName = teacherName;
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

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	@Override
	public String toString() {
		return "StudentSchedule [courseName=" + courseName + ", courseId=" + courseId + ", date=" + date + ", time="
				+ time + ", room=" + room + ", teacherName=" + teacherName + "]";
	}

}
