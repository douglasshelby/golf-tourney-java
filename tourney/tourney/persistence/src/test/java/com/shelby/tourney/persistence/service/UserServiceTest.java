package com.shelby.tourney.persistence.service;


import java.sql.Timestamp;
import java.util.Calendar;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextLoader;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.util.Assert;

import com.shelby.tourney.persistence.domain.User;
import com.shelby.tourney.persistence.service.UserService;
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.shelby.tourney.persistence")
@ContextConfiguration("classpath:persistence.xml")
public class UserServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	static Logger log = LoggerFactory.getLogger(UserServiceTest.class);
	@Autowired
	UserService userService;

    @Test
    public void testUserServiceReference(){
    	Assert.notNull(userService);
    }
    
    @Test
    @Rollback
    public void testUserInsert(){
    	User user = new User();
    	user.setEmail("douglas.shelby@gmail.com");
    	user.setPassword("password");
    	user.setUserId("douglas.shelby");
    	user.setLastLoginTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
    	user.setRegisterTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
    	log.debug("about to add user");
    	userService.addUser(user);
     	log.debug("user added");
    }
}
