package com.HostelMS.service;

import com.HostelMS.exception.GlobalException;

public interface userdashboard {

	public void dashboard() throws GlobalException;
	
	public void viewRoom();
    
	public void viewDueAmmount();
	
	public void viewProfile();
	
	public void changePhonenumber();
	
	public void changePassword() throws GlobalException;
	

}