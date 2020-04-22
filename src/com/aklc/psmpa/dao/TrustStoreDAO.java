package com.aklc.psmpa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.aklc.psmpa.util.MySQLUtility;

public class TrustStoreDAO {
	public void addIdentityTrustStore(String pid, String email) throws Exception {
		Connection con = null;
		try {
			con = MySQLUtility.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into PATIENT_IDENTITY_TRUST_STORE values (?,?) ");
			ps.setString(1, pid);
			ps.setString(2, email);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}
	}

	public void removeIdentityTrustStore(String pid, String email) throws Exception {
		Connection con = null;
		try {
			con = MySQLUtility.getConnection();
			PreparedStatement ps = con
					.prepareStatement("delete from PATIENT_IDENTITY_TRUST_STORE where pid=? and email=? ");
			ps.setString(1, pid);
			ps.setString(2, email);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}

	}

	public void addHealthInfoTrustStore(String visit_id, String email) throws Exception {
		Connection con = null;
		try {
			con = MySQLUtility.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into PATIENT_HEALTH_INFO_TRUST_STORE values (?,?) ");
			ps.setString(1, visit_id);
			ps.setString(2, email);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}
	}

	public void removehealthInfoTrustStore(String visit_id, String email) throws Exception {
		Connection con = null;
		try {
			con = MySQLUtility.getConnection();
			PreparedStatement ps = con
					.prepareStatement("delete from PATIENT_HEALTH_INFO_TRUST_STORE where visit_id=? and email=? ");
			ps.setString(1, visit_id);
			ps.setString(2, email);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}

	}

}
