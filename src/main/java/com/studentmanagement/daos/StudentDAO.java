package com.studentmanagement.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.studentmanagement.models.Student;
import com.studentmanagement.models.StudentSchedule;
import com.studentmanagement.models.TeacherSchedule;

@Repository
public class StudentDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public ArrayList<Student> getAllStudent() {
		String sql = "SELECT maSV, hoVaTen, gioiTinh, ngaySinh, queQuan, soDienThoai, email, matKhau, phanQuyen, maLop, maKhoa "
				   + "FROM sinhvien "
				   + "ORDER BY maSV, hoVaTen";
		return (ArrayList<Student>) jdbcTemplate.query(sql, new RowMapper<Student>() {
			@Override
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student student = new Student();
				student.setStudentId(rs.getString("maSV"));
				student.setName(rs.getString("hoVaTen"));
				student.setGender(rs.getString("gioiTinh"));
				student.setBirthday(rs.getDate("ngaySinh"));
				student.setHometown(rs.getString("queQuan"));
				student.setPhoneNumber(rs.getString("soDienThoai"));
				student.setEmail(rs.getString("email"));
				student.setPassword(rs.getString("matKhau"));
				student.setRole(rs.getString("phanQuyen"));
				student.setClassId(rs.getString("maLop"));
				student.setDepartmentId(rs.getString("maKhoa"));
				return student;
			}
		});
	}
	
	public Student getStudentById(String studentId) {
		String sql = "SELECT maSV, hoVaTen, gioiTinh, ngaySinh, queQuan, soDienThoai, email, matKhau, phanQuyen, maLop, maKhoa "
				   + "FROM sinhvien "
				   + "WHERE maSV = ?";
		return (Student) jdbcTemplate.queryForObject(sql, new RowMapper<Student>() {
			@Override
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student student = new Student();
				student.setStudentId(rs.getString("maSV"));
				student.setName(rs.getString("hoVaTen"));
				student.setGender(rs.getString("gioiTinh"));
				student.setBirthday(rs.getDate("ngaySinh"));
				student.setHometown(rs.getString("queQuan"));
				student.setPhoneNumber(rs.getString("soDienThoai"));
				student.setEmail(rs.getString("email"));
				student.setPassword(rs.getString("matKhau"));
				student.setRole(rs.getString("phanQuyen"));
				student.setClassId(rs.getString("maLop"));
				student.setDepartmentId(rs.getString("maKhoa"));
				return student;
			}
		}, studentId);
	}
	
	public ArrayList<Student> getStudentByDepartmentId(String departmentId) {
		String sql = "SELECT maSV, hoVaTen, gioiTinh, ngaySinh, queQuan, soDienThoai, email, matKhau, phanQuyen, maLop, maKhoa "
				   + "FROM sinhvien "
				   + "WHERE maKhoa = ?";
		return (ArrayList<Student>) jdbcTemplate.query(sql, new RowMapper<Student>() {
			@Override
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student student = new Student();
				student.setStudentId(rs.getString("maSV"));
				student.setName(rs.getString("hoVaTen"));
				student.setGender(rs.getString("gioiTinh"));
				student.setBirthday(rs.getDate("ngaySinh"));
				student.setHometown(rs.getString("queQuan"));
				student.setPhoneNumber(rs.getString("soDienThoai"));
				student.setEmail(rs.getString("email"));
				student.setPassword(rs.getString("matKhau"));
				student.setRole(rs.getString("phanQuyen"));
				student.setClassId(rs.getString("maLop"));
				student.setDepartmentId(rs.getString("maKhoa"));
				return student;
			}
		}, departmentId);
	}
	
	public ArrayList<Student> getStudentByClassId(String classId) {
		String sql = "SELECT maSV, hoVaTen, gioiTinh, ngaySinh, queQuan, soDienThoai, email, matKhau, phanQuyen, maLop, maKhoa "
				   + "FROM sinhvien "
				   + "WHERE maLop = ?";
		return (ArrayList<Student>) jdbcTemplate.query(sql, new RowMapper<Student>() {
			@Override
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student student = new Student();
				student.setStudentId(rs.getString("maSV"));
				student.setName(rs.getString("hoVaTen"));
				student.setGender(rs.getString("gioiTinh"));
				student.setBirthday(rs.getDate("ngaySinh"));
				student.setHometown(rs.getString("queQuan"));
				student.setPhoneNumber(rs.getString("soDienThoai"));
				student.setEmail(rs.getString("email"));
				student.setPassword(rs.getString("matKhau"));
				student.setRole(rs.getString("phanQuyen"));
				student.setClassId(rs.getString("maLop"));
				student.setDepartmentId(rs.getString("maKhoa"));
				return student;
			}
		}, classId);
	}
	
	public ArrayList<Student> getStudentByClassIdAndDepartmentId(String classId, String departmentId) {
		String sql = "SELECT maSV, hoVaTen, gioiTinh, ngaySinh, queQuan, soDienThoai, email, matKhau, phanQuyen, maLop, maKhoa "
				   + "FROM sinhvien "
				   + "WHERE maLop = ? AND maKhoa = ?";
		return (ArrayList<Student>) jdbcTemplate.query(sql, new RowMapper<Student>() {
			@Override
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student student = new Student();
				student.setStudentId(rs.getString("maSV"));
				student.setName(rs.getString("hoVaTen"));
				student.setGender(rs.getString("gioiTinh"));
				student.setBirthday(rs.getDate("ngaySinh"));
				student.setHometown(rs.getString("queQuan"));
				student.setPhoneNumber(rs.getString("soDienThoai"));
				student.setEmail(rs.getString("email"));
				student.setPassword(rs.getString("matKhau"));
				student.setRole(rs.getString("phanQuyen"));
				student.setClassId(rs.getString("maLop"));
				student.setDepartmentId(rs.getString("maKhoa"));
				return student;
			}
		}, classId, departmentId);
	}
	
	public void updateStudent(Student student) {
		String sql = "UPDATE sinhvien "
				   + "SET "
				   + "maSV = ?, "
				   + "hoVaTen = ?, "
				   + "gioiTinh = ?, "
				   + "ngaySinh = ?, "
				   + "queQuan = ?, "
				   + "soDienThoai = ?, "
				   + "email = ?, "
				   + "matKhau = ?, "
				   + "phanQuyen = ?, "
				   + "maLop = ?, "
				   + "maKhoa = ? "
				   + "WHERE maSV = ?";
		jdbcTemplate.update(sql, student.getStudentId(), student.getName(), student.getGender(), student.getBirthday(), student.getHometown(), student.getPhoneNumber(), student.getEmail(), student.getPassword(), student.getRole(), student.getClassId(), student.getDepartmentId(), student.getStudentId());
	}
	
	public void deleteStudentById(String studentId) {
		String sql = "DELETE FROM sinhvien WHERE maSV = ?";
		jdbcTemplate.update(sql, studentId);
	}
	
	public void addStudent(Student student) {
		String sql = "INSERT INTO sinhvien(maSV, hoVaTen, gioiTinh, ngaySinh, queQuan, soDienThoai, email, matKhau, phanQuyen, maLop, maKhoa) "
				   + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, student.getStudentId(), student.getName(), student.getGender(), student.getBirthday(), student.getHometown(), student.getPhoneNumber(), student.getEmail(), student.getPassword(), student.getRole(), student.getClassId(), student.getDepartmentId());
	}
	
	public ArrayList<StudentSchedule> getScheduleByStudentId(String studentId) {
		String sql = "SELECT hocphan.tenHP, hocphan.maHP, hocphan.ngayHoc, hocphan.caHoc, hocphan.phongHoc, giangvien.hoVaTen "
				   + "FROM thoikhoabieu JOIN hocphan ON thoikhoabieu.maHP = hocphan.maHP JOIN giangvien ON hocphan.maGV = giangvien.maGV JOIN sinhvien ON thoikhoabieu.maSV = sinhvien.maSV "
				   + "WHERE sinhvien.maSV = ? "
				   + "ORDER BY hocphan.ngayHoc, hocphan.caHoc;";
		return (ArrayList<StudentSchedule>) jdbcTemplate.query(sql, new RowMapper<StudentSchedule>() {
			@Override
			public StudentSchedule mapRow(ResultSet rs, int rowNum) throws SQLException {
				StudentSchedule studentSchedule = new StudentSchedule();
				studentSchedule.setCourseName(rs.getString("tenHP"));
				studentSchedule.setCourseId(rs.getString("maHP"));
				studentSchedule.setDate(rs.getString("ngayHoc"));
				studentSchedule.setTime(rs.getString("caHoc"));
				studentSchedule.setRoom(rs.getString("phongHoc"));
				studentSchedule.setTeacherName(rs.getString("hoVaTen"));
				return studentSchedule;
			}
		}, studentId);
	}
}
