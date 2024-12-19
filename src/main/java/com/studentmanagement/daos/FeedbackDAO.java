package com.studentmanagement.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.studentmanagement.models.Course;
import com.studentmanagement.models.Feedback;
import com.studentmanagement.models.Teacher;

@Repository
public class FeedbackDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int saveFeedback(Feedback feedback) {
		String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm"));
		String sql = "INSERT INTO danhgia(noiDung, thoiGianTao) "
				   + "VALUES(?, ?)";
		return jdbcTemplate.update(sql, feedback.getContent(), time);
	}
	
	public ArrayList<Feedback> getAllFeedback() {
		String sql = "SELECT maDG, noiDung, thoiGianTao, trangThai "
				   + "FROM danhgia "
				   + "ORDER BY maDG";
		return (ArrayList<Feedback>) jdbcTemplate.query(sql, new RowMapper<Feedback>() {
			@Override
			public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
				Feedback feedback = new Feedback();
				feedback.setFeedbackId(rs.getInt("maDG"));
				feedback.setContent(rs.getString("noiDung"));
				feedback.setTimeCreated(rs.getString("thoiGianTao"));
				feedback.setStatus(rs.getString("trangThai"));
				return feedback;
			}
		});
	}
	
	public ArrayList<Feedback> getFeedbackByStatus(String status) {
		String sql = "SELECT maDG, noiDung, thoiGianTao, trangThai "
				   + "FROM danhgia "
				   + "WHERE trangThai = ? "
				   + "ORDER BY maDG";
		return (ArrayList<Feedback>) jdbcTemplate.query(sql, new RowMapper<Feedback>() {
			@Override
			public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
				Feedback feedback = new Feedback();
				feedback.setFeedbackId(rs.getInt("maDG"));
				feedback.setContent(rs.getString("noiDung"));
				feedback.setTimeCreated(rs.getString("thoiGianTao"));
				feedback.setStatus(rs.getString("trangThai"));
				return feedback;
			}
		}, status);
	}
	
	public void updateFeedbackStatusById(String feedBackId) {
		String sql = "UPDATE danhgia "
				   + "SET "
				   + "trangThai = ? "
				   + "WHERE maDG = ?";
		jdbcTemplate.update(sql, "Đã duyệt", feedBackId);
	}
	
	public void deleteFeedbackById(String feedbackId) {
		String sql = "DELETE FROM danhgia WHERE maDG = ?";
		jdbcTemplate.update(sql, feedbackId);
	}
}
