package com.studentmanagement.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.studentmanagement.models.Course;
import com.studentmanagement.models.ScoreCourse;

@Repository
public class ScoreCourseDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public ArrayList<ScoreCourse> getAllScoreCourseByStudentId(String studentId) {
		String sql = "SELECT hocphan.maHP, hocphan.tenHP, sinhvien.maSV, sinhvien.hoVaTen, diem1, diem2, diemThi, diemTongKet10, diemTongKet4, diemChu, xepLoai "
				   + "FROM diemhocphan INNER JOIN hocphan ON diemhocphan.maHP = hocphan.maHP INNER JOIN sinhvien ON diemhocphan.maSV = sinhvien.maSV "
				   + "WHERE diemhocphan.maSV = ? "
				   + "ORDER BY hocphan.tenHP";
		return (ArrayList<ScoreCourse>) jdbcTemplate.query(sql, new RowMapper<ScoreCourse>() {
			@Override
			public ScoreCourse mapRow(ResultSet rs, int rowNum) throws SQLException {
				ScoreCourse scoreCourse = new ScoreCourse();
				scoreCourse.setCourseId(rs.getString("maHP"));
				scoreCourse.setCourseName(rs.getString("tenHP"));
				scoreCourse.setStudentId(rs.getString("maSV"));
				scoreCourse.setStudentName(rs.getString("hoVaTen"));
				scoreCourse.setScore1(rs.getObject("diem1") == null ? -1 : rs.getDouble("diem1"));
				scoreCourse.setScore2(rs.getObject("diem2") == null ? -1 : rs.getDouble("diem2"));
				scoreCourse.setScoreFinal(rs.getObject("diemThi") == null ? -1 : rs.getDouble("diemThi"));
				scoreCourse.setScoreOverall10(rs.getObject("diemTongKet10") == null ? -1 : rs.getDouble("diemTongKet10"));
				scoreCourse.setScoreOverall4(rs.getObject("diemTongKet4") == null ? -1 : rs.getDouble("diemTongKet4"));
				scoreCourse.setScoreAlpha(rs.getString("diemChu"));
				scoreCourse.setRank(rs.getString("xepLoai"));
				return scoreCourse;
			}
		}, studentId);
	}
	
	public ArrayList<ScoreCourse> getScoreCourseByCourseId(String courseId) {
		String sql = "SELECT hocphan.maHP, hocphan.tenHP, sinhvien.maSV, sinhvien.hoVaTen, diem1, diem2, diemThi "
				   + "FROM hocphan JOIN thoikhoabieu ON hocphan.maHP = thoikhoabieu.maHP JOIN sinhvien ON thoikhoabieu.maSV = sinhvien.maSV "
				   + "LEFT JOIN diemhocphan ON hocphan.maHP = diemhocphan.maHP AND sinhvien.maSV = diemhocphan.maSV "
				   + "WHERE hocphan.maHP = ? "
				   + "ORDER BY sinhvien.hoVaTen";
		return (ArrayList<ScoreCourse>) jdbcTemplate.query(sql, new RowMapper<ScoreCourse>() {
			@Override
			public ScoreCourse mapRow(ResultSet rs, int rowNum) throws SQLException {
				ScoreCourse scoreCourse = new ScoreCourse();
				scoreCourse.setCourseId(rs.getString("maHP"));
				scoreCourse.setCourseName(rs.getString("tenHP"));
				scoreCourse.setStudentId(rs.getString("maSV"));
				scoreCourse.setStudentName(rs.getString("hoVaTen"));
				scoreCourse.setScore1(rs.getObject("diem1") == null ? -1 : rs.getDouble("diem1"));
				scoreCourse.setScore2(rs.getObject("diem2") == null ? -1 : rs.getDouble("diem2"));
				scoreCourse.setScoreFinal(rs.getObject("diemThi") == null ? -1 : rs.getDouble("diemThi"));
				return scoreCourse;
			}
		}, courseId);
	}

	public int updateScoreForStudent(ScoreCourse scoreCourse) {
		String sql = "INSERT INTO diemhocphan (maHP, maSV, diem1, diem2, diemThi) "
				   + "SELECT hocphan.maHP, sinhvien.maSV, ?, ?, ? "
				   + "FROM hocphan "
				   + "JOIN thoikhoabieu ON hocphan.maHP = thoikhoabieu.maHP "
				   + "JOIN sinhvien ON thoikhoabieu.maSV = sinhvien.maSV "
				   + "WHERE hocphan.maHP = ? AND sinhvien.maSV = ?"
				   + "ON DUPLICATE KEY UPDATE diem1 = VALUES(diem1), diem2 = VALUES(diem2), diemThi = VALUES(diemThi);";
		return jdbcTemplate.update(sql, scoreCourse.getScore1(), scoreCourse.getScore2(), scoreCourse.getScoreFinal(), scoreCourse.getCourseId(), scoreCourse.getStudentId());
	}
	
	public double getGPAByStudentId(String studentId) {
		String sql = "SELECT maSV, ROUND(SUM(diemTongKet4 * soTC) / SUM(soTC), 2) AS GPA "
				   + "FROM diemhocphan INNER JOIN hocphan ON diemhocphan.maHP = hocphan.maHP "
				   + "WHERE maSV = ? "
				   + "GROUP BY maSV";
		return (double) jdbcTemplate.queryForObject(sql, new RowMapper<Double>() {
			@Override
			public Double mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getDouble("GPA");
			}
		}, studentId);
	}
}
