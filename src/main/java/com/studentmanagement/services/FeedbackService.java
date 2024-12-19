package com.studentmanagement.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmanagement.daos.FeedbackDAO;
import com.studentmanagement.models.Feedback;

@Service
public class FeedbackService {
	@Autowired
	private FeedbackDAO feedbackDAO;
	
	public int saveFeedback(Feedback feedback) {
		return feedbackDAO.saveFeedback(feedback);
	}
	
	public ArrayList<Feedback> getAllFeedback() {
		return feedbackDAO.getAllFeedback();
	}
	
	public ArrayList<Feedback> getFeedbackByStatus(String status) {
		if(status.equals("all")) {
			return feedbackDAO.getAllFeedback();
		}
		else {
			return feedbackDAO.getFeedbackByStatus(status);
		}
	}
	
	public void updateFeedbackStatusById(String feedBackId) {
		feedbackDAO.updateFeedbackStatusById(feedBackId);
	}
	
	public void deleteFeedbackById(String feedbackId) {
		feedbackDAO.deleteFeedbackById(feedbackId);
	}
}
