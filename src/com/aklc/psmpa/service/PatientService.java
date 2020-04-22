package com.aklc.psmpa.service;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.spec.RSAPrivateKeySpec;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.aklc.psmpa.crypto.DigitalSignatureImpl;
import com.aklc.psmpa.crypto.RSAImpl;
import com.aklc.psmpa.dao.CryptoDAO;
import com.aklc.psmpa.dao.PatientDAO;
import com.aklc.psmpa.dao.TrustStoreDAO;
import com.aklc.psmpa.pojo.CryptoPojo;
import com.aklc.psmpa.pojo.Patient;
import com.aklc.psmpa.util.AppConstant;
import com.aklc.psmpa.util.AppUtil;

public class PatientService {

	public void registerNewVisitDetails(Visit visit, List<String> initialtrust, String patientEmail) throws Exception {

		try {
			String patientID = visit.getPatientID();
			String visitID = AppUtil.generateVisitID();
			String data = visit.getDiseaseName() + AppConstant.DELIMITER + visit.getObservation()
					+ AppConstant.DELIMITER + visit.getPrescription();

			RSAImpl rsa = new RSAImpl();
			rsa.generateNewKeys();
			System.out.println("Raw data .. " + data);
			String patientDataEncoded = rsa.encrypt(data);

			DigitalSignatureImpl digi = new DigitalSignatureImpl();
			KeyFactory keyfact = KeyFactory.getInstance("RSA");
			System.out.println("Encoded data .. " + patientDataEncoded);
			String signature = digi.sign(
					keyfact.generatePrivate(new RSAPrivateKeySpec(rsa.priv_modulus, rsa.priv_exponent)),
					patientDataEncoded);
			System.out.println("Signature generated .. " + signature);
			CryptoPojo cryptoPojo = new CryptoPojo();
			cryptoPojo.setSignature(signature);
			cryptoPojo.setModulus(String.valueOf(rsa.pub_modulus));
			cryptoPojo.setExponent(String.valueOf(rsa.pub_exponent));
			cryptoPojo.setId(visitID);
			cryptoPojo.setData(patientDataEncoded);
			cryptoPojo.setPriv_modulus(String.valueOf(rsa.priv_modulus));
			cryptoPojo.setPriv_exponent(String.valueOf(rsa.priv_exponent));

			System.out.println("Encrypted data .. " + patientDataEncoded);
			PatientDAO patientDao = new PatientDAO();
			patientDao.writeNewVisitData(cryptoPojo, initialtrust, patientID, patientEmail);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	public void updateVisitDetails(Visit visit) throws Exception {

		try {
			String data = visit.getDiseaseName() + AppConstant.DELIMITER + visit.getObservation()
					+ AppConstant.DELIMITER + visit.getPrescription();

			RSAImpl rsa = new RSAImpl();
			rsa.generateNewKeys();
			System.out.println("Raw data .. " + data);
			String patientDataEncoded = rsa.encrypt(data);

			DigitalSignatureImpl digi = new DigitalSignatureImpl();
			KeyFactory keyfact = KeyFactory.getInstance("RSA");
			System.out.println("Encoded data .. " + patientDataEncoded);
			String signature = digi.sign(
					keyfact.generatePrivate(new RSAPrivateKeySpec(rsa.priv_modulus, rsa.priv_exponent)),
					patientDataEncoded);
			System.out.println("Signature generated .. " + signature);
			CryptoPojo cryptoPojo = new CryptoPojo();
			cryptoPojo.setSignature(signature);
			cryptoPojo.setModulus(String.valueOf(rsa.pub_modulus));
			cryptoPojo.setExponent(String.valueOf(rsa.pub_exponent));
			cryptoPojo.setId(visit.getVisitID());
			cryptoPojo.setData(patientDataEncoded);
			cryptoPojo.setPriv_modulus(String.valueOf(rsa.priv_modulus));
			cryptoPojo.setPriv_exponent(String.valueOf(rsa.priv_exponent));

			System.out.println("Encrypted data .. " + patientDataEncoded);
			PatientDAO patientDao = new PatientDAO();
			patientDao.updateVisitData(cryptoPojo);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	public void registerNewPatient(Patient patient, String initialtrust) throws Exception {

		try {
			String patientID = AppUtil.generatePatientID();
			String patientIdentitydata = patient.getEmail() + AppConstant.DELIMITER + patient.getFirstName()
					+ AppConstant.DELIMITER + patient.getLastName() + AppConstant.DELIMITER + patient.getGender()
					+ AppConstant.DELIMITER + patient.getDob() + AppConstant.DELIMITER + patient.getPhone()
					+ AppConstant.DELIMITER + patient.getAddress();
			RSAImpl rsa = new RSAImpl();
			rsa.generateNewKeys();
			String patientDataEncoded = rsa.encrypt(patientIdentitydata);

			DigitalSignatureImpl digi = new DigitalSignatureImpl();
			KeyFactory keyfact = KeyFactory.getInstance("RSA");
			System.out.println("Encoded data .. " + patientDataEncoded);
			String signature = digi.sign(
					keyfact.generatePrivate(new RSAPrivateKeySpec(rsa.priv_modulus, rsa.priv_exponent)),
					patientDataEncoded);
			System.out.println("Signature generated .. " + signature);
			CryptoPojo cryptoPojo = new CryptoPojo();
			cryptoPojo.setSignature(signature);
			cryptoPojo.setModulus(String.valueOf(rsa.pub_modulus));
			cryptoPojo.setExponent(String.valueOf(rsa.pub_exponent));
			cryptoPojo.setId(patientID);
			cryptoPojo.setData(patientDataEncoded);
			cryptoPojo.setPriv_modulus(String.valueOf(rsa.priv_modulus));
			cryptoPojo.setPriv_exponent(String.valueOf(rsa.priv_exponent));

			System.out.println("Encrypted data .. " + patientDataEncoded);
			PatientDAO patientDao = new PatientDAO();
			patientDao.writePatientIdentity(cryptoPojo, initialtrust);
			TrustStoreDAO trustStoreDao = new TrustStoreDAO();
			trustStoreDao.addIdentityTrustStore(patientID, patient.getEmail());

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	public Patient readPatientIdentity(String patientID, String requestor) throws Exception {
		try {
			PatientDAO patientDao = new PatientDAO();
			CryptoDAO cryptoDao = new CryptoDAO();

			if (cryptoDao.isIdentityTrustVerified(patientID, requestor)) {

				if (cryptoDao.validateIdentitySignature(patientID)) {
					String encryptedData = patientDao.readpatientIdentity(patientID);
					System.out.println("Encrypted data .. " + encryptedData);
					Map<String, BigInteger> keys = cryptoDao.getIdentityKeys(patientID);
					RSAImpl rsa = new RSAImpl(keys.get("modulus"), keys.get("exponent"));
					String decryptedData = rsa.decrypt(encryptedData);
					System.out.println("Decrypted data .. " + decryptedData);
					StringTokenizer st = new StringTokenizer(decryptedData, AppConstant.DELIMITER);
					Patient patient = new Patient();
					patient.setPatientID(patientID);
					patient.setEmail(st.nextToken());
					patient.setFirstName(st.nextToken());
					patient.setLastName(st.nextToken());
					patient.setGender(st.nextToken());
					patient.setDob(st.nextToken());
					patient.setPhone(st.nextToken());
					patient.setAddress(st.nextToken());
					return patient;
				} else {
					return null;
				}

			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public Visit readVisitDetails(String visitId, String requestor) throws Exception {
		try {
			PatientDAO patientDao = new PatientDAO();
			CryptoDAO cryptoDao = new CryptoDAO();
			if (cryptoDao.isHealthInfoTrustVerified(visitId, requestor)) {

				if (cryptoDao.validateHealthInfoSignature(visitId)) {
					Map<String, String> visitDetails = patientDao.readpatientHealthInfo(visitId);
					System.out.println("Encrypted data .. " + visitDetails.get("data"));
					Map<String, BigInteger> keys = cryptoDao.getHealthInfoKeys(visitId);
					RSAImpl rsa = new RSAImpl(keys.get("modulus"), keys.get("exponent"));
					String decryptedData = rsa.decrypt(visitDetails.get("data"));
					System.out.println("Decrypted data .. " + decryptedData);
					StringTokenizer st = new StringTokenizer(decryptedData, AppConstant.DELIMITER);
					Visit visit = new Visit();
					visit.setPatientID(visitDetails.get("pid"));
					visit.setVisitID(visitId);
					visit.setEntryTime(Timestamp.valueOf(visitDetails.get("entryTime")));
					visit.setUpdateTime(Timestamp.valueOf(visitDetails.get("updateTime")));
					visit.setDiseaseName(st.nextToken());
					visit.setObservation(st.nextToken());
					visit.setPrescription(st.nextToken());

					return visit;
				} else {
					System.out.println(1);
					return null;
				}

			} else {
				System.out.println(2);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
