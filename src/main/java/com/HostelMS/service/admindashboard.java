package com.HostelMS.service;

import com.HostelMS.exception.GlobalException;

public interface admindashboard {
     public void viewRooms();
     public void viewUsers();
     public void userInARoom();
	 public void dashboard() throws GlobalException;
	 public void createRoom ();
     public void allotRoom() throws GlobalException;
     public void deleteUser() throws GlobalException;
     public void addDueAmount() throws GlobalException;
     public void paidDueAmount() throws GlobalException;
     public void viewUserProfile();
	
	
}
