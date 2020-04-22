package com.aklc.psmpa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aklc.psmpa.pojo.CryptoPojo;
import com.aklc.psmpa.service.Visit;
import com.aklc.psmpa.util.MySQLUtility;

public class PatientDAO {

	public List<String> getPatientIdentityTrustStore(String pid) throws Exception {
		Connection con = null;
		try {
			con = MySQLUtility.getConnection();
			List<String> result = new ArrayList<>();
			PreparedStatement ps = con.prepareStatement("select email from PATIENT_IDENTITY_TRUST_STORE where pid=?");
			ps.setString(1, pid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result.add(rs.getString("email"));
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}

	}

	public List<String> getAllPatientIds() throws Exception {

		Connection con = null;
		try {
			con = MySQLUtility.getConnection();
			List<String> result = new ArrayList<>();
			ResultSet rs = con.createStatement().executeQuery("select pid from PATIENT_IDENTITY");
			while (rs.next()) {
				result.add(rs.getString(1));
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}
	}

	public List<String> getAllVisitIds(String pid) throws Exception {
		Connection con = null;
		try {
			con = MySQLUtility.getConnection();
			List<String> result = new ArrayList<>();
			PreparedStatement ps = con.prepareStatement("select visit_id from PATIENT_HEALTH_INFO where pid=?");
			ps.setString(1, pid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result.add(rs.getString(1));
			}
			return result;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}
	}

	public List<String> getAllVisitIds() throws Exception {
		Connection con = null;
		try {
			con = MySQLUtility.getConnection();
			List<String> result = new ArrayList<>();
			PreparedStatement ps = con.prepareStatement("select visit_id from PATIENT_HEALTH_INFO");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result.add(rs.getString(1));
			}
			return result;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}
	}

	public void writePatientIdentity(CryptoPojo cryptoPojo, String initialTrust) throws Exception {
		Connection con = null;
		try {
			con = MySQLUtility.getConnection();
			con.setAutoCommit(false);

			PreparedStatement ps1 = con.prepareStatement("insert into PATIENT_IDENTITY values (?,?) ");
			ps1.setString(1, cryptoPojo.getId());
			ps1.setString(2, cryptoPojo.getData());
			ps1.execute();
			System.out.println("Done writing to PATIENT_IDENTITY table");

			PreparedStatement ps2 = con.prepareStatement("insert into RSAKEYS_IDENTITY values (?,?,?,?,?,?) ");
			ps2.setString(1, cryptoPojo.getId());
			ps2.setString(2, cryptoPojo.getModulus());
			ps2.setString(3, cryptoPojo.getExponent());
			ps2.setString(4, cryptoPojo.getSignature());
			ps2.setString(5, cryptoPojo.getPriv_modulus());
			ps2.setString(6, cryptoPojo.getPriv_exponent());
			ps2.execute();
			System.out.println("Done writing to RSAKEYS_IDENTITY table");

			PreparedStatement ps3 = con.prepareStatement("insert into PATIENT_IDENTITY_TRUST_STORE values (?,?) ");
			ps3.setString(1, cryptoPojo.getId());
			ps3.setString(2, initialTrust);
			ps3.execute();

			System.out.println("Done writing to PATIENT_IDENTITY_TRUST_STORE table");

			con.commit();
			System.out.println("Transaction Committed");
		} catch (Exception e) {
			e.printStackTrace();
			con.rollback();
			System.out.println("Rolled back the transaction");
			throw e;
		} finally {
			con.close();
		}
	}

	public String readpatientIdentity(String patientID) throws Exception {
		Connection con = null;
		try {
			con = MySQLUtility.getConnection();
			PreparedStatement ps = con.prepareStatement("select data from PATIENT_IDENTITY where pid=?");
			ps.setString(1, patientID);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}
	}

	public void deletePatientRecord(String patientID) throws Exception {
		Connection con = null;
		try {
			System.out.println("Ashok .. pid .. " + patientID);
			con = MySQLUtility.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps1 = con.prepareStatement("delete from PATIENT_IDENTITY where pid=?");
			ps1.setString(1, patientID);
			ps1.execute();
			PreparedStatement ps = con.prepareStatement("select visit_id from patient_health_info where pid=?");
			ps.setString(1, patientID);
			ResultSet rs = ps.executeQuery();
			List<String> vid = new ArrayList<>();
			while (rs.next()) {
				vid.add(rs.getString("visit_id"));
			}
			PreparedStatement ps2 = con.prepareStatement("delete from PATIENT_HEALTH_INFO where pid=?");
			ps2.setString(1, patientID);
			ps2.execute();

			for (String v : vid) {
				PreparedStatement ps3 = con
						.prepareStatement("delete from PATIENT_HEALTH_INFO_TRUST_STORE where visit_id=?");
				ps3.setString(1, v);
				ps3.execute();
			}
			PreparedStatement ps4 = con.prepareStatement("delete from PATIENT_IDENTITY_TRUST_STORE where pid=?");
			ps4.setString(1, patientID);
			ps4.execute();

			for (String v : vid) {
				PreparedStatement ps5 = con.prepareStatement("delete from RSAKEYS_HEALTH_INFO where visit_id=?");
				ps5.setString(1, v);
				ps5.execute();
			}
			PreparedStatement ps6 = con.prepareStatement("delete from RSAKEYS_IDENTITY where pid=?");
			ps6.setString(1, patientID);
			ps6.execute();

			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			con.rollback();
			throw e;
		} finally {
			con.close();
		}
	}

	public Map<String, String> readpatientHealthInfo(String visitId) throws Exception {
		Connection con = null;
		try {
			con = MySQLUtility.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from PATIENT_HEALTH_INFO where visit_id=?");
			ps.setString(1, visitId);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Map<String, String> result = new HashMap<>();
			result.put("pid", rs.getString("pid"));
			result.put("data", rs.getString("data"));
			result.put("entryTime", String.valueOf(rs.getTimestamp("entryTime")));
			result.put("updateTime", String.valueOf(rs.getTimestamp("updateTime")));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}
	}

	public void writeNewVisitData(CryptoPojo cryptoPojo, List<String> initialtrust, String patientID,
			String patientEmail) throws Exception {
		Connection con = null;
		try {
			con = MySQLUtility.getConnection();
			con.setAutoCommit(false);

			PreparedStatement ps1 = con.prepareStatement(
					"insert into PATIENT_HEALTH_INFO (visit_id, pid, data, entrytime, updatetime) values (?,?,?,?,?) ");
			ps1.setString(1, cryptoPojo.getId());
			ps1.setString(2, patientID);
			ps1.setString(3, cryptoPojo.getData());
			ps1.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			ps1.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			ps1.execute();
			System.out.println("Done writing to PATIENT_HEALTH_INFO table");

			PreparedStatement ps2 = con.prepareStatement("insert into RSAKEYS_HEALTH_INFO values (?,?,?,?,?,?) ");
			ps2.setString(1, cryptoPojo.getId());
			ps2.setString(2, cryptoPojo.getModulus());
			ps2.setString(3, cryptoPojo.getExponent());
			ps2.setString(4, cryptoPojo.getSignature());
			ps2.setString(5, cryptoPojo.getPriv_modulus());
			ps2.setString(6, cryptoPojo.getPriv_exponent());
			ps2.execute();
			System.out.println("Done writing to RSAKEYS_HEALTH_INFO table");

			for (String trust : initialtrust) {
				PreparedStatement ps3 = con
						.prepareStatement("insert into PATIENT_HEALTH_INFO_TRUST_STORE values (?,?) ");
				ps3.setString(1, cryptoPojo.getId());
				ps3.setString(2, trust);
				ps3.execute();
			}

			PreparedStatement ps4 = con.prepareStatement("insert into PATIENT_HEALTH_INFO_TRUST_STORE values (?,?) ");
			ps4.setString(1, cryptoPojo.getId());
			ps4.setString(2, patientEmail);
			ps4.execute();

			System.out.println("Done writing to PATIENT_HEALTH_INFO_TRUST_STORE table");

			con.commit();
			System.out.println("Transaction Committed");
		} catch (Exception e) {
			e.printStackTrace();
			con.rollback();
			System.out.println("Rolled back the transaction");
			throw e;
		} finally {
			con.close();
		}

	}

	public void updateVisitData(CryptoPojo cryptoPojo) throws Exception {
		Connection con = null;
		try {
			con = MySQLUtility.getConnection();
			con.setAutoCommit(false);

			PreparedStatement ps1 = con
					.prepareStatement("update PATIENT_HEALTH_INFO set data=?, updatetime = ? where visit_id=? ");
			ps1.setString(1, cryptoPojo.getData());
			ps1.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			ps1.setString(3, cryptoPojo.getId());
			ps1.execute();
			System.out.println("Done updating PATIENT_HEALTH_INFO table");

			PreparedStatement ps2 = con.prepareStatement(
					"update RSAKEYS_HEALTH_INFO set modulus=?, exponent=?, signature=?, priv_modulus=?, priv_exponent=? where visit_id=? ");
			ps2.setString(1, cryptoPojo.getModulus());
			ps2.setString(2, cryptoPojo.getExponent());
			ps2.setString(3, cryptoPojo.getSignature());
			ps2.setString(4, cryptoPojo.getPriv_modulus());
			ps2.setString(5, cryptoPojo.getPriv_exponent());
			ps2.setString(6, cryptoPojo.getId());

			ps2.execute();
			System.out.println("Done updating RSAKEYS_HEALTH_INFO table");

			con.commit();
			System.out.println("Transaction Committed");
		} catch (Exception e) {
			e.printStackTrace();
			con.rollback();
			System.out.println("Rolled back the transaction");
			throw e;
		} finally {
			con.close();
		}
	}

	public Map<String, List<String>> getPatientHealthInfoTrustStore(String pid) throws Exception {
		Connection con = null;
		try {
			con = MySQLUtility.getConnection();
			PreparedStatement ps = con.prepareStatement("select visit_id from PATIENT_HEALTH_INFO where pid=?  ");
			ps.setString(1, pid);
			ResultSet rs = ps.executeQuery();
			Map<String, List<String>> result = new HashMap<>();
			while (rs.next()) {
				String vid = rs.getString("visit_id");
				PreparedStatement ps2 = con
						.prepareStatement("select email from PATIENT_HEALTH_INFO_TRUST_STORE where visit_id=? ");
				ps2.setString(1, vid);
				ResultSet rs2 = ps2.executeQuery();
				List<String> emails = new ArrayList<>();
				while (rs2.next()) {
					emails.add(rs2.getString("email"));
				}
				result.put(vid, emails);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}
	}

}
