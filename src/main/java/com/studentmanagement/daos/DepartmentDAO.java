package com.studentmanagement.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.studentmanagement.models.Department;
import com.studentmanagement.models.Teacher;

@Repository
public class DepartmentDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public ArrayList<Department> getAllDepartment() {
		String sql = "SELECT * FROM khoa "
				   + "ORDER BY maKhoa, tenKhoa";
		return (ArrayList<Department>) jdbcTemplate.query(sql, new RowMapper<Department>() {
			@Override
			public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
				Department department = new Department();
				department.setDepartmentId(rs.getString("maKhoa"));
				department.setName(rs.getString("tenKhoa"));
				return department;
			}
		});
	}
}
