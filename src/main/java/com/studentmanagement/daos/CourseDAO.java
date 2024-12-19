package com.studentmanagement.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.studentmanagement.models.Course;
import com.studentmanagement.models.Teacher;

@Repository
public class CourseDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Course getCourseById(String courseId) {
		String sql = "SELECT hp.maHP, hp.tenHP, hp.soTC, hp.ngayHoc, hp.caHoc, hp.phongHoc, hp.maGV, gv.hoVaTen AS tenGiangVien, COUNT(tkb.maSV) AS soSinhVien "
				   + "FROM hocphan hp "
				   + "INNER JOIN giangvien gv ON hp.maGV = gv.maGV "
				   + "LEFT JOIN thoikhoabieu tkb ON hp.maHP = tkb.maHP "
				   + "WHERE hp.maHP = ? "
				   + "GROUP BY hp.maHP, hp.tenHP, hp.soTC, hp.ngayHoc, hp.caHoc, hp.phongHoc, gv.hoVaTen";
		return (Course) jdbcTemplate.queryForObject(sql, new RowMapper<Course>() {
			@Override
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
				Course course = new Course();
				course.setCourseId(rs.getString("maHP"));
				course.setName(rs.getString("tenHP"));
				course.setCredit(rs.getInt("soTC"));
				course.setDate(rs.getString("ngayHoc"));
				course.setTime(rs.getString("caHoc"));
				course.setRoom(rs.getString("phongHoc"));
				course.setTeacherId(rs.getString("maGV"));
				course.setTeacherName(rs.getString("tenGiangVien"));
				course.setNumberStudent(rs.getInt("soSinhVien"));
				return course;
			}
		}, courseId);
	}
	
	public ArrayList<Course> getAllCourse() {
		String sql = "SELECT hp.maHP, hp.tenHP, hp.soTC, hp.ngayHoc, hp.caHoc, hp.phongHoc, hp.maGV, gv.hoVaTen AS tenGiangVien, COUNT(tkb.maSV) AS soSinhVien "
				   + "FROM hocphan hp "
				   + "INNER JOIN giangvien gv ON hp.maGV = gv.maGV "
				   + "LEFT JOIN thoikhoabieu tkb ON hp.maHP = tkb.maHP "
				   + "GROUP BY hp.maHP, hp.tenHP, hp.soTC, hp.ngayHoc, hp.caHoc, hp.phongHoc, gv.hoVaTen";
		return (ArrayList<Course>) jdbcTemplate.query(sql, new RowMapper<Course>() {
			@Override
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
				Course course = new Course();
				course.setCourseId(rs.getString("maHP"));
				course.setName(rs.getString("tenHP"));
				course.setCredit(rs.getInt("soTC"));
				course.setDate(rs.getString("ngayHoc"));
				course.setTime(rs.getString("caHoc"));
				course.setRoom(rs.getString("phongHoc"));
				course.setTeacherId(rs.getString("maGV"));
				course.setTeacherName(rs.getString("tenGiangVien"));
				course.setNumberStudent(rs.getInt("soSinhVien"));
				return course;
			}
		});
	}
	
	public void updateCourse(Course course) {
		String sql = "UPDATE hocphan "
				   + "SET "
				   + "maHP = ?, "
				   + "tenHP = ?, "
				   + "soTC = ?, "
				   + "ngayHoc = ?, "
				   + "caHoc = ?, "
				   + "phongHoc = ?, "
				   + "maGV = ? "
				   + "WHERE maHP = ?";
		System.out.println(course);
		jdbcTemplate.update(sql, course.getCourseId(), course.getName(), course.getCredit(), course.getDate(), course.getTime(), course.getRoom(), course.getTeacherId(), course.getCourseId());
	}
	
	public void deleteCourseById(String courseId) {
		String sql = "DELETE FROM hocphan WHERE maHP = ?";
		jdbcTemplate.update(sql, courseId);
	}
	
	public void addCourse(Course course) {
		String sql = "INSERT INTO hocphan(maHP, tenHP, soTC, ngayHoc, caHoc, phongHoc, maGV) "
				   + "VALUES(?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, course.getCourseId(), course.getName(), course.getCredit(), course.getDate(), course.getTime(), course.getRoom(), course.getTeacherId());
	}
}
