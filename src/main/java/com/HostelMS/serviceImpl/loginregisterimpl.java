package com.HostelMS.serviceImpl;


import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.HostelMS.App;
import com.HostelMS.dao.hostelMSDao;
import com.HostelMS.daoImpl.hostelMSDaoImpl;
import com.HostelMS.exception.GlobalException;
import com.HostelMS.model.user;
import com.HostelMS.service.admindashboard;
import com.HostelMS.service.loginregister;
import com.HostelMS.service.userdashboard;

public class loginregisterimpl implements loginregister {
	static Logger log=Logger.getLogger(App.class);
	static Scanner bs=new Scanner(System.in);
	static hostelMSDao dao=new hostelMSDaoImpl();
	
	//registration method
	public void register() throws GlobalException{
		log.info("welcome to registeration");
		log.info("Enter Username");
		String uname=bs.next();
		log.info("Create Password");
		String upwd=bs.next();
		log.info("Enter Phone number");
		String uphone=bs.next();
		log.info("Enter Address");
		String uaddress=bs.next();
		
		user u1=new user();
		u1.setUserName(uname);
		u1.setUserPassword(upwd);
		u1.setUserPhone(uphone);
		u1.setUserAddress(uaddress);
		u1.setUserRole("student");
		u1.setUserRoom(null);
		u1.setUserFee(0);
		//regular expressions to check data correctness
		if(Pattern.matches("[a-zA-Z]{4,}", uname)&&Pattern.matches("[a-zA-Z0-9@#]{6,}",upwd)&&Pattern.matches("[0-9]{10}", uphone))
		{
			//saving the user details
			int status=dao.registration(u1);
			//log.info(status);
			if(status==1) {
				log.info("Registration success");
			}
			else {
				throw new GlobalException("Something went wrong");
			}
		}
		else {
			throw new GlobalException("Invalid data");
		}
}

	public void login()throws GlobalException {
		log.info("---welcome ;) to Login---");
		
		log.info("Enter username");
		String username=bs.next();
		log.info("Enter password");
		String password=bs.next();
		//checking login
		user u1=dao.login(username, password);
		//success message
		log.info("Hello "+u1.getUserName()+" Login Success");
		userdashboard udl=new userdashboardImpl();
		admindashboard adl=new admindashboardImpl();
		//if userrole is student userdashboard will open
		//if userrole is admin admindashboard will open
		if(u1.getUserRole().equals("student")) {
			udl.dashboard();
		}
		else if(u1.getUserRole().equals("admin")) {
			adl.dashboard();
		}
	}

}