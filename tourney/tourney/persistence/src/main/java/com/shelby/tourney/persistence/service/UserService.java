package com.shelby.tourney.persistence.service;

import java.util.List;

import com.shelby.tourney.persistence.domain.User;

public interface UserService {

	public void addUser(User user);
	public List<User> listUser();
	public void removeUser(Integer id);
}
