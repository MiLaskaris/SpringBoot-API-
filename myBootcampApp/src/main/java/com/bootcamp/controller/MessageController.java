package com.bootcamp.controller;

import com.bootcamp.service.MessageService;
import com.bootcamp.entity.Message;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@RequestMapping(method = RequestMethod.GET)
	public Collection<Message> getAllMessages(){
		return messageService.getAllMessages();
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public Message getMEssageByID(@PathVariable("id") int id) {
		return messageService.getMessageById(id);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public void deleteMessageById(@PathVariable("id") int id) {
		messageService.removeMessageById(id);
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateMessage(@RequestBody Message message) {
		messageService.updateMessage(message);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertMessage(@RequestBody Message message) {
		messageService.insertMessage(message);
	}
	
}