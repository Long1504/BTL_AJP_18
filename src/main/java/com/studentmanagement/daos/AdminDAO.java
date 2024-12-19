package com.studentmanagement.daos;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.studentmanagement.models.Admin;
import com.studentmanagement.models.Teacher;

@Repository
public class AdminDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Admin getAdminById(String adminId) {
		String sql = "SELECT * FROM quantri WHERE maQT = ?";
		return (Admin) jdbcTemplate.queryForObject(sql, new RowMapper<Admin>() {
			@Override
			public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
				Admin admin = new Admin();
				admin.setAdminId(rs.getString("maQT"));
				admin.setName(rs.getString("hoVaTen"));
				admin.setPassword(rs.getString("matKhau"));
				admin.setRole(rs.getString("phanQuyen"));
				return admin;
			}
		}, adminId);
	}

}
