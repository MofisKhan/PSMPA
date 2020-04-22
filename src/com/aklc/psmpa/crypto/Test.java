package com.aklc.psmpa.crypto;

import java.security.KeyFactory;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

public class Test {

	public static void main(String[] args) throws Exception {
//
//		RSAImpl rsa = new RSAImpl();
//		rsa.generateNewKeys();
//		String plainData = "Hello Ashok Kumar. Good Morning. !!";
//		String encryptedData = rsa.encrypt(plainData);
//
//		System.out.println(encryptedData);
//
//		String decryptedData = rsa.decrypt(encryptedData);
//		System.out.println(decryptedData);
//
//		DigitalSignatureImpl d = new DigitalSignatureImpl();
//		KeyFactory keyfact = KeyFactory.getInstance("RSA");
//		String signData = d.sign(keyfact.generatePrivate(new RSAPrivateKeySpec(rsa.priv_modulus, rsa.priv_exponent)),
//				encryptedData);
//		System.out.println(signData);
//
//		Boolean verify = d.verify(keyfact.generatePublic(new RSAPublicKeySpec(rsa.pub_modulus, rsa.pub_exponent)),
//				encryptedData, signData);
//
//		System.out.println(verify);
		
		
		double res = Math.pow(103,48) % 143;
		System.out.println(res);
	}

}
