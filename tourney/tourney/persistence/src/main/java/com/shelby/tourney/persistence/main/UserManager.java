package com.shelby.tourney.persistence.main;

import java.sql.Timestamp;
import java.util.Calendar;

import org.hibernate.Session;

import com.shelby.tourney.persistence.domain.User;
import com.shelby.tourney.persistence.utils.HibernateUtil;

public class UserManager {
    public static void main(String[] args) {
        UserManager mgr = new UserManager();

        if (args[0].equals("store")) {
            mgr.createAndStoreUser("douglas.shelby@gmail.com","douglas.shelby","password",new Timestamp(Calendar.getInstance().getTime().getTime()),
            		new Timestamp(Calendar.getInstance().getTime().getTime()));
        }

        HibernateUtil.getSessionFactory().close();
    }
    
    private void createAndStoreUser(String email, String userId, String password,
			Timestamp registerTime, Timestamp lastLoginTime){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        User user= new User();
        user.setEmail(email);
        user.setRegisterTime(registerTime);
        user.setLastLoginTime(lastLoginTime);
        
        session.getTransaction().commit();
    }
}
