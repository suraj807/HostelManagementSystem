/* Hostel Management System is used to allot Room to the Student
Hostel Management System is designed with JPA, HIBERNATE, 
MySQL and perfoming unit testing with JUNIT 5 with two user
->End User
->Admin user
 *Yogesh Sharma
 */
package com.HostelMS;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.HostelMS.exception.GlobalException;
import com.HostelMS.service.loginregister;
import com.HostelMS.serviceImpl.loginregisterimpl;

//main method of hostel management system
public class App {
	// Using loggers to print data & result instead of system.out....
	static Logger log = Logger.getLogger(App.class);

	public static void main(String[] args) throws GlobalException { // Scanner object
		Scanner sc = new Scanner(System.in);
		log.info("\t\t\t\t\t---------Hostel Management System----------");

		// creating object of loginregisterimpl() method with the reference of loginRegister
		loginregister loginreg = new loginregisterimpl();
		log.info("\nPress 1 for Registeration\nPress 2 for Login\nPress ");
		int op = sc.nextInt();

		// switch case to choose any option to both user
		switch (op) {
			//calling login & register methods using object 
			case 1 -> loginreg.register();
			case 2 -> loginreg.login();
		}
		sc.close();
	}
}