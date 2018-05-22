package com.bootcamp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bootcamp.entity.Message;

@Repository
public class MessageDaoImpl implements MessageDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Collection<Message> getAllMessages() {
		final String sql = "SELECT * FROM message";
		List<Message> messages = jdbcTemplate.query(sql, new RowMapper<Message>() {

			public Message mapRow(ResultSet resultSet, int i) throws SQLException {
				Message message = new Message();
				message.setId(resultSet.getInt("ID"));
				message.setSender(resultSet.getString("Sender"));
				message.setReceiver(resultSet.getString("Receiver"));
				message.setBody(resultSet.getString("Body"));
				message.setTime(resultSet.getString("Date"));
				return message;
			}

		});
		return messages;
	}

	public Message getMessageById(int id) {
		final String sql = "SELECT * FROM message where id = ?";
		Message message = jdbcTemplate.queryForObject(sql, new RowMapper<Message>() {

			public Message mapRow(ResultSet resultSet, int i) throws SQLException {
				Message message = new Message();
				message.setId(resultSet.getInt("ID"));
				message.setSender(resultSet.getString("Sender"));
				message.setReceiver(resultSet.getString("Receiver"));
				message.setBody(resultSet.getString("Body"));
				message.setTime(resultSet.getString("Date"));
				return message;
			}
		}, id);
		return message;
	}

	public void removeMessageById(int id) {
		final String sql = "DELETE FROM message where id = ?";
		jdbcTemplate.update(sql, id);

	}

	public void updateMessage(Message message) {
		final String sql ="UPDATE message SET Sender = ?, Receiver = ?, Body = ? , Date = ? WHERE id = ?";
		int id = message.getId();
		String sender = message.getSender();
		String receiver = message.getReceiver();
		String body = message.getBody();
		String date = message.getTime();
		jdbcTemplate.update(sql,new Object[] {sender,receiver,body,date,id});

	}

	public void insertMessage(Message message) {
		final String sql ="INSERT INTO message (Id,Sender,Receiver,Body,Date) VALUES (?, ?, ?, ?, ?)";
		int id = message.getId();
		String sender = message.getSender();
		String receiver = message.getReceiver();
		String body = message.getBody();
		String time = message.getTime();
		jdbcTemplate.update(sql,new Object[] {sender,receiver,body,time,id});

	}

}
