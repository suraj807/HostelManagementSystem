//imlementation of the methods which can maintain by admin
package com.HostelMS.daoImpl;

import java.util.List;

import javax.persistence.Query;

import com.HostelMS.config.HibernateUtil;
import com.HostelMS.dao.adminDao;
import com.HostelMS.exception.GlobalException;
import com.HostelMS.model.*;

import org.hibernate.Session;

public class adminDaoImpl implements adminDao {

	@Override
	//method to create room
	public int createRoom(room r1) throws GlobalException {
		// TODO Auto-generated method stub
		try (Session ses = HibernateUtil.getSession()) {

			ses.beginTransaction();

			String roomName = r1.getRoomName();

			room r2 = null;
            // To get data of r2 if available
			r2 = (room) ses.createQuery("from room where roomName =:roomName").setParameter("roomName", roomName)
					.uniqueResult();
            //checking the condition
			if (r2 == null) {

				ses.save(r1);
				ses.getTransaction().commit();
				return 1;
			} else {
                //throw a new exception if r2 is not null
				throw new GlobalException("room is already exist");
			}

		}

	}

	@Override
	//method to allot room to user
	public int allotRoom(int rId, int uId) {
		// TODO Auto-generated method stub
		try (Session ses = HibernateUtil.getSession()) {

			ses.beginTransaction();
            // HQL query to alloting the room to any user
			int st = ses.createQuery("update user set userRoom_roomId =:rId where userId=:uId ")
					.setParameter("uId", uId).setParameter("rId", rId).executeUpdate();

			ses.getTransaction().commit();

			return st;
		}
	}

	@Override
	//method to delete any user  
	public int deleteUser(int uId) {
		// TODO Auto-generated method stub
		try (Session ses = HibernateUtil.getSession()) {

			ses.beginTransaction();

			int st = ses.createQuery("delete from user where userId =:uId").setParameter("uId", uId).executeUpdate();

			return st;

		}
	}

	@Override
	//method to add due over the user
	public int addDueAmount(int amount, int uId) {
		// TODO Auto-generated method stub
		try (Session ses = HibernateUtil.getSession()) {

			ses.beginTransaction();

			user u = ses.get(user.class, uId);
			int dueFee = u.getUserFee();
			dueFee += amount;
            
			//HQL Query to adding the dues to user 
			int st = ses.createQuery("update user set userFee =:dueFee where userId =:uId")
					.setParameter("dueFee", dueFee).setParameter("uId", uId).executeUpdate();

			return st;
		}
	}

	@Override
	//method to declare total amount left to be paid by the user
	public int paidDueAmount(int amount, int uId) {
		// TODO Auto-generated method stub
		try (Session ses = HibernateUtil.getSession()) {

			ses.beginTransaction();

			int dueFee = (int) ses.createQuery("select userFee from user where userId =:uId").setParameter("uId", uId)
					.uniqueResult();

			dueFee -= amount;

			int st = ses.createQuery("update user set userFee =:dueFee where userId =:uId")
					.setParameter("dueFee", dueFee).setParameter("uId", uId).executeUpdate();

			return st;

		}
	}

	@Override
	//method to view user all details
	public user viewUserProfile(int uId) {
		// TODO Auto-generated method stub
		try (Session ses = HibernateUtil.getSession()) {

			ses.beginTransaction();

			user u1 = ses.get(user.class, uId);

			return u1;
		}
	}

	@Override
	//method to view all the rooms existed in the hostel
	public List<room> viewRooms() {
		// autoclosable session object
		try (Session ses = HibernateUtil.getSession()) {
			// getting rows of a room table
			Query q = ses.createQuery("from room");
			@SuppressWarnings("unchecked")
			List<room> rl = q.getResultList();
			return rl;
		}
	}

	@Override
	//method to view all the users 
	public List<user> viewUsers() {
		// TODO Auto-generated method stub
		try (Session ses = HibernateUtil.getSession()) {
             
			Query q = ses.createQuery("from user");
			@SuppressWarnings("unchecked")
			List<user> ul = q.getResultList();
			return ul;
		}
	}

	@Override
	public List<user> userInARoom(int rId) {
		// TODO Auto-generated method stub
		try (Session ses = HibernateUtil.getSession()) {

			Query q = ses.createQuery("from user where userRoom_roomId =:rId").setParameter("rId", rId);

			List<user> rl = q.getResultList();
			return rl;
		}
	}

}
