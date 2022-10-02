/* This interface is declare to achiecve abstraction
 * and declare the methods which can maintain by admin
 */

package com.HostelMS.dao;

import java.util.List;

import com.HostelMS.exception.GlobalException;
import com.HostelMS.model.*;

public interface adminDao {
     public List<room>viewRooms();
     public List<user>viewUsers();
     public int createRoom (room r1) throws GlobalException;
     public int allotRoom(int rId, int uId );
     public int deleteUser(int uId);
     public int addDueAmount(int amount , int uId);
     public int paidDueAmount(int amount , int uId );
     public user viewUserProfile(int uId);
     public List<user>userInARoom(int rId);
     
}
