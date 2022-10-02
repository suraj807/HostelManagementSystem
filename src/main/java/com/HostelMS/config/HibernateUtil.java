/* THis class is to create/establish connection with database
 * using hibernate framework 
 */
package com.HostelMS.config;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.HostelMS.model.room;
import com.HostelMS.model.user;

public class HibernateUtil {

	// session factory used to provide connection of session and client
	private static SessionFactory sesFactory;

	public static SessionFactory getSessionFactory() {

		if (sesFactory == null) {

			try {
				// Instance of configuration() method
				Configuration config = new Configuration();
				Properties pro = new Properties();
				// To add property of the Persistent class.
				pro.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
				pro.put(Environment.URL, "jdbc:mysql://localhost:3306/hostelMS");
				pro.put(Environment.USER, "root");
				pro.put(Environment.PASS, "root");
				pro.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
				pro.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				pro.put(Environment.SHOW_SQL, "false");
				pro.put(Environment.HBM2DDL_AUTO, "update");

				config.setProperties(pro);
				config.addAnnotatedClass(user.class);
				config.addAnnotatedClass(room.class);

				// building a session
				sesFactory = config.buildSessionFactory();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return sesFactory;

	}

	// method to get session & open new session
	public static Session getSession() {
		return getSessionFactory().openSession();
	}

}