package com.bootcamp.dao;

import java.util.Collection;

import com.bootcamp.entity.Message;

public interface MessageDao {

	Collection<Message> getAllMessages();

	Message getMessageById(int id);

	void removeMessageById(int id);

	void updateMessage(Message message);

	void insertMessage(Message message);

}