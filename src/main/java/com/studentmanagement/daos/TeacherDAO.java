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
import com.studentmanagement.models.TeacherSchedule;

@org.springframework.transaction.annotation.Transactional
@Repository
public class TeacherDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private DepartmentDAO departmentDAO;

	public ArrayList<Teacher> getAllTeacher() {
		String sql = "SELECT maGV, hoVaTen, gioiTinh, ngaySinh, queQuan, soDienThoai, email, matKhau, phanQuyen, khoa.maKhoa, tenKhoa "
				   + "FROM giangvien INNER JOIN khoa ON giangvien.maKhoa = khoa.maKhoa "
				   + "ORDER BY maGV, hoVaTen";
		return (ArrayList<Teacher>) jdbcTemplate.query(sql, new RowMapper<Teacher>() {
			@Override
			public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
				Teacher teacher = new Teacher();
				teacher.setTeacherId(rs.getString("maGV"));
				teacher.setName(rs.getString("hoVaTen"));
				teacher.setGender(rs.getString("gioiTinh"));
				teacher.setBirthday(rs.getDate("ngaySinh"));
				teacher.setHometown(rs.getString("queQuan"));
				teacher.setPhoneNumber(rs.getString("soDienThoai"));
				teacher.setEmail(rs.getString("email"));
				teacher.setPassword(rs.getString("matKhau"));
				teacher.setRole(rs.getString("phanQuyen"));
				teacher.setDepartmentId(rs.getString("maKhoa"));
				teacher.setDepartment(rs.getString("tenKhoa"));
				return teacher;
			}
		});
	}

	public Teacher getTeacherById(String teacherId) {
		String sql = "SELECT maGV, hoVaTen, gioiTinh, ngaySinh, queQuan, soDienThoai, email, matKhau, phanQuyen, khoa.maKhoa, tenKhoa "
				   + "FROM giangvien INNER JOIN khoa ON giangvien.maKhoa = khoa.maKhoa "
				   + "WHERE giangvien.maGV = ?";
		return (Teacher) jdbcTemplate.queryForObject(sql, new RowMapper<Teacher>() {
			@Override
			public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
				Teacher teacher = new Teacher();
				teacher.setTeacherId(rs.getString("maGV"));
				teacher.setName(rs.getString("hoVaTen"));
				teacher.setGender(rs.getString("gioiTinh"));
				teacher.setBirthday(rs.getDate("ngaySinh"));
				teacher.setHometown(rs.getString("queQuan"));
				teacher.setPhoneNumber(rs.getString("soDienThoai"));
				teacher.setEmail(rs.getString("email"));
				teacher.setPassword(rs.getString("matKhau"));
				teacher.setRole(rs.getString("phanQuyen"));
				teacher.setDepartmentId(rs.getString("maKhoa"));
				teacher.setDepartment(rs.getString("tenKhoa"));
				return teacher;
			}
		}, teacherId);
	}
	
	public ArrayList<Teacher> getTeacherByDepartmentName(String departmentName) {
		String sql = "SELECT maGV, hoVaTen, gioiTinh, ngaySinh, queQuan, soDienThoai, email, matKhau, phanQuyen, khoa.maKhoa, tenKhoa "
				   + "FROM giangvien INNER JOIN khoa ON giangvien.maKhoa = khoa.maKhoa "
				   + "WHERE tenKhoa = ? "
				   + "ORDER BY maGV, hoVaTen";
		return (ArrayList<Teacher>) jdbcTemplate.query(sql, new RowMapper<Teacher>() {
			@Override
			public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
				Teacher teacher = new Teacher();
				teacher.setTeacherId(rs.getString("maGV"));
				teacher.setName(rs.getString("hoVaTen"));
				teacher.setGender(rs.getString("gioiTinh"));
				teacher.setBirthday(rs.getDate("ngaySinh"));
				teacher.setHometown(rs.getString("queQuan"));
				teacher.setPhoneNumber(rs.getString("soDienThoai"));
				teacher.setEmail(rs.getString("email"));
				teacher.setPassword(rs.getString("matKhau"));
				teacher.setRole(rs.getString("phanQuyen"));
				teacher.setDepartmentId(rs.getString("maKhoa"));
				teacher.setDepartment(rs.getString("tenKhoa"));
				return teacher;
			}
		}, departmentName);
	}

	public int updateTeacher(Teacher teacher) {
		String sql = "UPDATE giangvien "
				   + "SET "
				   + "maGV = ?, "
				   + "hoVaTen = ?, "
				   + "gioiTinh = ?, "
				   + "ngaySinh = ?, "
				   + "queQuan = ?, "
				   + "soDienThoai = ?, "
				   + "email = ?, "
				   + "matKhau = ?, "
				   + "phanQuyen = ?, "
				   + "maKhoa = ? "
				   + "WHERE maGV = ?";
		System.out.println(teacher);
		return jdbcTemplate.update(sql, teacher.getTeacherId(), teacher.getName(), teacher.getGender(), teacher.getBirthday(), teacher.getHometown(), teacher.getPhoneNumber(), teacher.getEmail(), teacher.getPassword(), teacher.getRole(), teacher.getDepartmentId(), teacher.getTeacherId());
	}
	
	public ArrayList<Course> getCourseByTeacher(String teacherId) {
		String sql = "SELECT hp.maHP, hp.tenHP, hp.soTC, hp.ngayHoc, hp.caHoc, hp.phongHoc, hp.maGV, gv.hoVaTen AS tenGiangVien, COUNT(tkb.maSV) AS soSinhVien "
				   + "FROM hocphan hp "
				   + "INNER JOIN giangvien gv ON hp.maGV = gv.maGV "
				   + "LEFT JOIN thoikhoabieu tkb ON hp.maHP = tkb.maHP "
				   + "WHERE gv.maGV = ? "
				   + "GROUP BY hp.maHP, hp.tenHP, hp.soTC, hp.ngayHoc, hp.caHoc, hp.phongHoc, gv.hoVaTen";
		return (ArrayList<Course>) jdbcTemplate.query(sql, new RowMapper<Course>() {
			@Override
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
				Course course = new Course();
				course.setCourseId(rs.getString("maHP"));
				course.setName(rs.getString("tenHP"));
				course.setCredit(rs.getInt("soTC"));
				course.setDate(rs.getString("ngayHoc"));
				course.setDate(rs.getString("caHoc"));
				course.setRoom(rs.getString("phongHoc"));
				course.setTeacherId(rs.getString("maGV"));
				course.setTeacherName(rs.getString("tenGiangVien"));
				course.setNumberStudent(rs.getInt("soSinhVien"));
				return course;
			}
		}, teacherId);
	}
	
	public ArrayList<TeacherSchedule> getScheduleByTeacher(String teacherId) {
		String sql = "SELECT hp.tenHP, hp.maHP, hp.ngayHoc, hp.caHoc, hp.phongHoc "
				   + "FROM hocphan hp "
				   + "INNER JOIN giangvien gv ON hp.maGV = gv.maGV "
				   + "WHERE gv.maGV = ?"
				   + "ORDER BY hp.ngayHoc, hp.caHoc";
		return (ArrayList<TeacherSchedule>) jdbcTemplate.query(sql, new RowMapper<TeacherSchedule>() {
			@Override
			public TeacherSchedule mapRow(ResultSet rs, int rowNum) throws SQLException {
				TeacherSchedule teacherSchedule = new TeacherSchedule();
				teacherSchedule.setCourseName(rs.getString("tenHP"));
				teacherSchedule.setCourseId(rs.getString("maHP"));
				teacherSchedule.setDate(rs.getString("ngayHoc"));
				teacherSchedule.setTime(rs.getString("caHoc"));
				teacherSchedule.setRoom(rs.getString("phongHoc"));
				return teacherSchedule;
			}
		}, teacherId);
	}
	
	public void deleteTeacherById(String teacherId) {
		String sql = "DELETE FROM giangvien WHERE maGV = ?";
		jdbcTemplate.update(sql, teacherId);
	}
	
	public void addTeacher(Teacher teacher) {
		String sql = "INSERT INTO giangvien(maGV, hoVaTen, gioiTinh, ngaySinh, queQuan, soDienThoai, email, matKhau, phanQuyen, maKhoa) "
				   + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, teacher.getTeacherId(), teacher.getName(), teacher.getGender(), teacher.getBirthday(), teacher.getHometown(), teacher.getPhoneNumber(), teacher.getEmail(), teacher.getPassword(), teacher.getRole(), teacher.getDepartmentId());
	}
}
