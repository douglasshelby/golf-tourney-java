package com.shelby.tourney.persistence.dao;

import java.util.List;

import com.shelby.tourney.persistence.domain.User;

public interface UserDao {

	public void addUser (User user);
	public List<User> listContact();
	public void removeUser(Integer id);
}
