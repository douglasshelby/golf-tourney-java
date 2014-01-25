package com.shelby.tourney.persistence.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.shelby.tourney.persistence.dao.UserDao;
import com.shelby.tourney.persistence.domain.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Transactional
	public void addUser(User user) {
		userDao.addUser(user);
		
	}

	public List<User> listUser() {
		return userDao.listContact();
	}

	public void removeUser(Integer id) {
		userDao.removeUser(id);
		
	}

}
