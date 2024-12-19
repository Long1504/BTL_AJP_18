package com.studentmanagement.models;

public class Course {
	private String courseId;
	private String name;
	private int credit;
	private String date;
	private String time;
	private String room;
	private String teacherId;
	private String teacherName;
	private int numberStudent;

	public Course() {
	}

	public Course(String courseId, String name, int credit, String date, String time, String room, String teacherId,
			String teacherName, int numberStudent) {
		this.courseId = courseId;
		this.name = name;
		this.credit = credit;
		this.date = date;
		this.time = time;
		this.room = room;
		this.teacherId = teacherId;
		this.teacherName = teacherName;
		this.numberStudent = numberStudent;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
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

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacher) {
		this.teacherName = teacher;
	}

	public int getNumberStudent() {
		return numberStudent;
	}

	public void setNumberStudent(int numberStudent) {
		this.numberStudent = numberStudent;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", name=" + name + ", credit=" + credit + ", date=" + date + ", time="
				+ time + ", room=" + room + ", teacherId=" + teacherId + ", teacherName=" + teacherName
				+ ", numberStudent=" + numberStudent + "]";
	}

}
