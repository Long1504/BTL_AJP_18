package com.studentmanagement.models;

public class ScoreCourse {
	private String courseId;
	private String courseName;
	private String studentId;
	private String studentName;
	private double score1;
	private double score2;
	private double scoreFinal;
	private double scoreOverall10;
	private double scoreOverall4;
	private String scoreAlpha;
	private String rank;

	public ScoreCourse() {
	}

	public ScoreCourse(String courseId, String courseName, String studentId, String studentName, double score1,
			double score2, double scoreFinal, double scoreOverall10, double scoreOverall4, String scoreAlpha,
			String rank) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.studentId = studentId;
		this.studentName = studentName;
		this.score1 = score1;
		this.score2 = score2;
		this.scoreFinal = scoreFinal;
		this.scoreOverall10 = scoreOverall10;
		this.scoreOverall4 = scoreOverall4;
		this.scoreAlpha = scoreAlpha;
		this.rank = rank;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public double getScore1() {
		return score1;
	}

	public void setScore1(double score1) {
		this.score1 = score1;
	}

	public double getScore2() {
		return score2;
	}

	public void setScore2(double score2) {
		this.score2 = score2;
	}

	public double getScoreFinal() {
		return scoreFinal;
	}

	public void setScoreFinal(double scoreFinal) {
		this.scoreFinal = scoreFinal;
	}

	public double getScoreOverall10() {
		return scoreOverall10;
	}

	public void setScoreOverall10(double scoreOverall10) {
		this.scoreOverall10 = scoreOverall10;
	}

	public double getScoreOverall4() {
		return scoreOverall4;
	}

	public void setScoreOverall4(double scoreOverall4) {
		this.scoreOverall4 = scoreOverall4;
	}

	public String getScoreAlpha() {
		return scoreAlpha;
	}

	public void setScoreAlpha(String scoreAlpha) {
		this.scoreAlpha = scoreAlpha;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "ScoreCourse [courseId=" + courseId + ", courseName=" + courseName + ", studentId=" + studentId
				+ ", studentName=" + studentName + ", score1=" + score1 + ", score2=" + score2 + ", scoreFinal="
				+ scoreFinal + ", scoreOverall10=" + scoreOverall10 + ", scoreOverall4=" + scoreOverall4
				+ ", scoreAlpha=" + scoreAlpha + ", rank=" + rank + "]";
	}

}
