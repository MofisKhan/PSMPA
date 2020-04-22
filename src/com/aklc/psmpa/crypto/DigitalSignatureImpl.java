package com.aklc.psmpa.crypto;

/*
 * Author : Ashok Kumar K 
 * 			AKLC | VTUPROJECTS.COM
 * 			9742024066 | celestialcluster@gmail.com
 */

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

public class DigitalSignatureImpl {
	
	/*
	 * The Signature class is used to provide applications the functionality of a digital signature algorithm. 
	 * Digital signatures are used for authentication and integrity assurance of digital data. 
	 */
	
	Signature sig;

	/*
	 * A constructor which initializes the digital signature process
	 * with the MD5 with RSA Algorithm
	 */
	public DigitalSignatureImpl() throws NoSuchAlgorithmException {
		sig = Signature.getInstance("MD5WithRSA");
	}
	
	/*
	 * function name: sign
	 * input: private key, data
	 * output: signed data
	 * methodology: This method signs the inputted data using the private key provided as the input and returns the 
	 * signature as an output
	 *  
	 */

	public String sign(PrivateKey privKey, String encryptedData)
			throws SignatureException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
		sig.initSign(privKey);
		sig.update((new BigInteger(encryptedData)).toByteArray());
		byte[] signatureBytes = sig.sign();

		return (new BigInteger(signatureBytes)).toString();
	}
	

	/*
	 * function name: verify
	 * input: public key, signature, data
	 * output: true/ false
	 * methodology: This method verifies if the data is authentic or not by using the verify algorithnm of digital signature process
	 *   
	 */

	public Boolean verify(PublicKey pubKey, String encryptedData, String signature)
			throws InvalidKeyException, NoSuchAlgorithmException, SignatureException, UnsupportedEncodingException {
		sig.initVerify(pubKey);
		sig.update((new BigInteger(encryptedData)).toByteArray());
		boolean result = sig.verify((new BigInteger(signature)).toByteArray());

		return result;
	}
}
