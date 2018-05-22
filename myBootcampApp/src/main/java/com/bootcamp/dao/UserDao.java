package com.bootcamp.dao;

import java.util.Collection;

import com.bootcamp.entity.User;

public interface UserDao {

	Collection<User> getAllUsers();

	User getUserById(int id);

	void removeUserById(int id);

	void updateUser(User user);

	void insertUser(User user);

}