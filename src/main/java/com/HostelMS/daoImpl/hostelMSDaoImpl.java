package com.HostelMS.daoImpl;

import org.hibernate.Session;

import com.HostelMS.config.HibernateUtil;
import com.HostelMS.dao.hostelMSDao;
import com.HostelMS.exception.GlobalException;
import com.HostelMS.model.user;

public class hostelMSDaoImpl implements hostelMSDao {

	@Override
	public int registration(user u1) throws GlobalException {
		
		try(Session ses=HibernateUtil.getSession())
		{
			
			String username=u1.getUserName();
			user u2=null;
			u2=(user)ses.createQuery("from user where userName=:username").setParameter("username", username).uniqueResult();
			if(u2==null)
			{
				ses.beginTransaction();
				ses.save(u1);
				ses.getTransaction().commit();
				return 1;	
			}
			else {
				throw new GlobalException("user already existed");
			}
			
		}
		
		
	}

	@Override
	public user login(String username, String password) throws GlobalException {
		
		try(Session ses=HibernateUtil.getSession()){
			ses.beginTransaction();
			
			user u2=null;
			u2=(user)ses.createQuery("from user where userName=:username").setParameter("username", username).uniqueResult();
			if(u2!=null)
			{
			if(u2.getUserPassword().equals(password)) {
				return u2;
			}
			else {
				throw new GlobalException("Wrong Username or Password");
			}
			}
			else {
				throw new GlobalException("Wrong Username or Password");
			}
			
		}
		
		
		
		
	}

}