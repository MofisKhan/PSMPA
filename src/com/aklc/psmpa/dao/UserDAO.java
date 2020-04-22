package com.aklc.psmpa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aklc.psmpa.util.MySQLUtility;

public class UserDAO {
	public void createAccount(String email, String password, String role) throws Exception {
		Connection con = null;
		try {
			con = MySQLUtility.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into USERS values (?,?,?) ");
			ps.setString(1, email);
			ps.setString(2, password);
			ps.setString(3, role);

			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}

	}

	public boolean isValidUser(String email, String password, String role) throws Exception {
		Connection con = null;
		try {

			con = MySQLUtility.getConnection();
			PreparedStatement ps = con
					.prepareStatement("select count(*) from USERS where email=? and role=? and password=? ");
			ps.setString(1, email);
			ps.setString(2, role);
			ps.setString(3, password);
			ResultSet rs = ps.executeQuery();
			rs.next();
			if (rs.getInt(1) > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}

	}

}
