/* This interface is declare to achiecve abstraction
 * and declare the methods which can maintain by 
 * user(admin/student) 
 */
package com.HostelMS.dao;

import com.HostelMS.exception.GlobalException;
import com.HostelMS.model.user;

public interface hostelMSDao {

	public int registration(user u1) throws GlobalException;

	public user login(String username, String password) throws GlobalException;

}
