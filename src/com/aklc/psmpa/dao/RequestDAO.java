package com.aklc.psmpa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.aklc.psmpa.pojo.Request;
import com.aklc.psmpa.util.MySQLUtility;

public class RequestDAO {
	public void createRequest(String id, String email, String type) throws Exception {
		Connection con = null;
		try {
			con = MySQLUtility.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into REQUESTS values (?,?,?, 'PENDING') ");
			ps.setString(1, type);
			ps.setString(2, id);
			ps.setString(3, email);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
	}
	
	public List<Request> getPatientIdentityRequests(String pid) throws Exception {
		Connection con = null;
		List<Request> requests = new ArrayList<>();
		try {
			con = MySQLUtility.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from REQUESTS where data_id=? and status='PENDING' ");
			ps.setString(1, pid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Request r = new Request();
				r.setEmail(rs.getString("email"));
				r.setStatus(rs.getString("status"));
				r.setId(rs.getString("data_id"));
				r.setType(rs.getString("request_type"));
				requests.add(r);
			}
			return requests;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}
	}

	public void updateRequest(Request r) throws Exception {
		Connection con = null;
		try {
			con = MySQLUtility.getConnection();
			PreparedStatement ps = con
					.prepareStatement("update REQUESTS set status=? where request_type=? and email=? and data_id=? ");
			ps.setString(1, r.getStatus());
			ps.setString(2, r.getType());
			ps.setString(3, r.getEmail());
			ps.setString(4, r.getId());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}
	}

	public List<Request> getAllRequestsForUser(String email) throws Exception {
		Connection con = null;
		List<Request> requests = new ArrayList<>();
		try {
			con = MySQLUtility.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from REQUESTS where email=?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Request r = new Request();
				r.setEmail(email);
				r.setStatus(rs.getString("status"));
				r.setId(rs.getString("data_id"));
				r.setType(rs.getString("request_type"));
				requests.add(r);
			}
			return requests;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}

	}

}
