package com.studentmanagement.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.studentmanagement.models.Class;

@Repository
public class ClassDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public ArrayList<Class> getAllClass() {
		String sql = "SELECT maLop, tenLop, maKhoa "
				   + "FROM lop "
				   + "ORDER BY maLop, tenLop";
		return (ArrayList<Class>) jdbcTemplate.query(sql, new RowMapper<Class>() {
			@Override
			public Class mapRow(ResultSet rs, int rowNum) throws SQLException {
				Class class1 = new Class();
				class1.setClassId(rs.getString("maLop"));
				class1.setName(rs.getString("tenLop"));
				class1.setDepartmentId(rs.getString("maKhoa"));
				return class1;
			}
		});
	}
}
