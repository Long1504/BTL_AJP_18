package com.studentmanagement.models;

public class Feedback {
	private int feedbackId;
	private String content;
	private String timeCreated;
	private String status;

	public Feedback() {
	}

	public Feedback(int feedbackId, String content, String timeCreated, String status) {
		this.feedbackId = feedbackId;
		this.content = content;
		this.status = status;
		this.timeCreated = timeCreated;
	}

	public int getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(String timeCreated) {
		this.timeCreated = timeCreated;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Feedback [feedbackId=" + feedbackId + ", content=" + content + ", status=" + status + "]";
	}

}
