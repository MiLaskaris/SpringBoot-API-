package com.bootcamp.service;

import com.bootcamp.dao.MessageDao;
import com.bootcamp.entity.Message;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

	@Autowired
	private MessageDao messageDao;

	public Collection<Message> getAllMessages() {
		return messageDao.getAllMessages();
	}

	public Message getMessageById(int id) {

		return this.messageDao.getMessageById(id);

	}

	public void removeMessageById(int id) {
		this.messageDao.removeMessageById(id);
		
	}
	
	public void updateMessage(Message message) {
		this.messageDao.updateMessage(message);
	}

	public void insertMessage(Message message) {
		
		messageDao.insertMessage(message);
		
	}

}
