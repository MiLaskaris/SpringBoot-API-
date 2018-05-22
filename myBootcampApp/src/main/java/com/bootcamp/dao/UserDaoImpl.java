package com.bootcamp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bootcamp.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Collection<User> getAllUsers() {
		final String sql = "SELECT * FROM user";
		List<User> users = jdbcTemplate.query(sql, new RowMapper<User>() {

			public User mapRow(ResultSet resultSet, int i) throws SQLException {
				User user = new User();
				user.setId(resultSet.getInt("ID"));
				user.setName(resultSet.getString("Username"));
				user.setPassword(resultSet.getString("Password"));
				return user;
			}

		});
		return users;
	}

	public User getUserById(int id) {
		final String sql = "SELECT * FROM user where id = ?";
		User user = jdbcTemplate.queryForObject(sql, new RowMapper<User>() {

			public User mapRow(ResultSet resultSet, int i) throws SQLException {
				User user = new User();
				user.setId(resultSet.getInt("ID"));
				user.setName(resultSet.getString("Username"));
				user.setPassword(resultSet.getString("Password"));
				return user;
			}
		}, id);
		return user;
	}

	public void removeUserById(int id) {
		final String sql = "DELETE FROM user where id = ?";
		jdbcTemplate.update(sql, id);

	}

	public void updateUser(User user) {
		final String sql ="UPDATE User SET Username = ?, Password = ? WHERE id = ?";
		int id = user.getId();
		String name = user.getName();
		String password = user.getPassword();
		jdbcTemplate.update(sql,new Object[] {name,password,id});

	}

	public void insertUser(User user) {
		final String sql ="INSERT INTO user (Username,Password) VALUES (?, ?)";
		String username = user.getName();
		String password = user.getPassword();
		jdbcTemplate.update(sql,new Object[] {username,password});
	}

}
