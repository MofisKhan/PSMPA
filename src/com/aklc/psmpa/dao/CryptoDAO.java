package com.aklc.psmpa.dao;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.aklc.psmpa.crypto.DigitalSignatureImpl;
import com.aklc.psmpa.util.MySQLUtility;

public class CryptoDAO {
	public Boolean validateIdentitySignature(String patientID) throws Exception {
		Connection con = null;
		try {
			con = MySQLUtility.getConnection();
			PreparedStatement ps = con.prepareStatement("select data from PATIENT_IDENTITY where pid=?");
			ps.setString(1, patientID);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String data = rs.getString(1);

			PreparedStatement ps2 = con.prepareStatement("select * from RSAKEYS_IDENTITY where pid=?");
			ps2.setString(1, patientID);
			ResultSet rs2 = ps2.executeQuery();
			rs2.next();
			String signature = rs2.getString("signature");
			BigInteger modulus = new BigInteger(rs2.getString("modulus"));
			BigInteger exponent = new BigInteger(rs2.getString("exponent"));

			RSAPublicKeySpec rsapubKey = new RSAPublicKeySpec(modulus, exponent);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey pubkey = keyFactory.generatePublic(rsapubKey);
			DigitalSignatureImpl digi = new DigitalSignatureImpl();
			System.out.println("Signature read .. " + signature);
			boolean result = digi.verify(pubkey, data, signature);

			System.out.println("Signature verification result .. " + result);
			return result;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}

	}

	public Boolean validateHealthInfoSignature(String patientID) throws Exception {
		Connection con = null;
		try {
			con = MySQLUtility.getConnection();
			PreparedStatement ps = con.prepareStatement("select data from PATIENT_HEALTH_INFO where visit_id=?");
			ps.setString(1, patientID);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String data = rs.getString(1);
			System.out.println("Data .. " + data);
			PreparedStatement ps2 = con.prepareStatement("select * from RSAKEYS_HEALTH_INFO where visit_id=?");
			ps2.setString(1, patientID);
			ResultSet rs2 = ps2.executeQuery();
			rs2.next();
			String signature = rs2.getString("signature");
			BigInteger modulus = new BigInteger(rs2.getString("modulus"));
			BigInteger exponent = new BigInteger(rs2.getString("exponent"));

			RSAPublicKeySpec rsapubKey = new RSAPublicKeySpec(modulus, exponent);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey pubkey = keyFactory.generatePublic(rsapubKey);
			DigitalSignatureImpl digi = new DigitalSignatureImpl();
			System.out.println("Signature read .. " + signature);
			boolean result = digi.verify(pubkey, data, signature);

			System.out.println("Signature verification result .. " + result);
			return result;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}

	}

	public Boolean isIdentityTrustVerified(String patientID, String requestor) throws Exception {
		Connection con = null;
		try {
			con = MySQLUtility.getConnection();
			PreparedStatement ps1 = con.prepareStatement(
					"select count(*) from PATIENT_IDENTITY_TRUST_STORE where pid=? and (email=? OR email=?) ");
			ps1.setString(1, patientID);
			ps1.setString(2, requestor);
			String[] arg = requestor.split("@");
			ps1.setString(3, "*@" + arg[1]);
			ResultSet rs = ps1.executeQuery();
			rs.next();
			if (rs.getInt(1) > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}
	}

	public Boolean isHealthInfoTrustVerified(String visit_id, String requestor) throws Exception {
		Connection con = null;
		try {
			con = MySQLUtility.getConnection();
			PreparedStatement ps1 = con.prepareStatement(
					"select count(*) from PATIENT_HEALTH_INFO_TRUST_STORE where visit_id=? and (email=? OR email=?) ");
			ps1.setString(1, visit_id);
			ps1.setString(2, requestor);
			String[] arg = requestor.split("@");
			ps1.setString(3, "*@" + arg[1]);
			System.out.println("*@" + arg[1]);
			ResultSet rs = ps1.executeQuery();
			rs.next();
			if (rs.getInt(1) > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}
	}

	public Map<String, BigInteger> getIdentityKeys(String patientID) throws Exception {
		Connection con = null;
		try {
			con = MySQLUtility.getConnection();
			Map<String, BigInteger> result = new HashMap<>();
			PreparedStatement ps = con.prepareStatement("select * from RSAKEYS_IDENTITY where pid=?");
			ps.setString(1, patientID);
			ResultSet rs = ps.executeQuery();
			rs.next();
			result.put("modulus", new BigInteger(rs.getString("priv_modulus")));
			result.put("exponent", new BigInteger(rs.getString("priv_exponent")));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}
	}

	public Map<String, BigInteger> getHealthInfoKeys(String visitID) throws Exception {
		Connection con = null;
		try {
			con = MySQLUtility.getConnection();
			Map<String, BigInteger> result = new HashMap<>();
			PreparedStatement ps = con.prepareStatement("select * from RSAKEYS_HEALTH_INFO where visit_id=?");
			ps.setString(1, visitID);
			ResultSet rs = ps.executeQuery();
			rs.next();
			result.put("modulus", new BigInteger(rs.getString("priv_modulus")));
			result.put("exponent", new BigInteger(rs.getString("priv_exponent")));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}
	}
}
