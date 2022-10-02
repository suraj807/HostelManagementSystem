package com.HostelMS.serviceImpl;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.HostelMS.dao.userDao;
import com.HostelMS.daoImpl.userDaoImpl;
import com.HostelMS.exception.GlobalException;
import com.HostelMS.model.user;
import com.HostelMS.service.userdashboard;

public class userdashboardImpl implements userdashboard {
	//declaring static objects to use in entire class
	static Logger log=Logger.getLogger(userdashboardImpl.class);
	static Scanner bs=new Scanner(System.in);
	static userdashboardImpl udl=new userdashboardImpl();
	static userDao dao=new userDaoImpl();
	static int userId;

	//view room details of the user
	@Override
	public void viewRoom() {
		//calling dao layer
	user u1=dao.viewRoom(userId);
	log.info("Hello "+u1.getUserName()+" your room number is"+u1.getUserRoom().getRoomId()+" room name is "+u1.getUserRoom().getRoomName()+" and it is "+u1.getUserRoom().getRoomType()+" room");
	}

	//view dueamount of the user
	@Override
	public void viewDueAmmount() {
		//calling dao layer
		int amount=dao.viewDueAmmount(userId);
		log.info("your fee due upto this month is :"+amount);
	}

	//viewProfile with toString 
	@Override
	public void viewProfile() {
		
		user u1=dao.viewProfile(userId);
		log.info(u1);
		
	}

	//to change phone number
	@Override
	public void changePhonenumber() {
		log.info("Enter New Phone number");
		String phone=bs.next();
		int st=dao.changePhonenumber(userId, phone);
		if(st==1) {
			log.info("Phone number updated");
		}
	}

	//to update password, user have to enter current password
	@Override
	public void changePassword() throws GlobalException {
		
		log.info("Enter Current Password");
		String oldpwd=bs.next();
		log.info("Enter New Password");
		String newpwd=bs.next();
		int st=dao.changePassword(userId, oldpwd, newpwd);
		if(st==1) {
			log.info("password changed");
		}
	}

	@Override
	public void dashboard() throws GlobalException {
		log.info("\t\t\t---------------------Welcome to userdashboard----------------------");
		int op=0;
		
		while(op<6) {
			//user can select operation
			log.info("\nPress 1 for viewRoom\nPress 2 for view dueAmount \nPress 3 for view profile\nPress 4 for Update Phone number \nPress 5 for Change password");
			
			op=bs.nextInt();
			
			switch(op) {
		
		case 1->udl.viewRoom();
		
		case 2->udl.viewDueAmmount();
		
		case 3->udl.viewProfile();
		
		case 4->udl.changePhonenumber();
		
		case 5->udl.changePassword();
		}
		}
	}

}