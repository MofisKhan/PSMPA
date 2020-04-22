package com.aklc.psmpa.crypto;

/*
 * Author : Ashok Kumar K 
 * 			AKLC | VTUPROJECTS.COM
 * 			9742024066 | celestialcluster@gmail.com
 */

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAImpl {

	// Private key modulus
	public BigInteger priv_modulus;

	// Private key exponent
	public BigInteger priv_exponent;

	// Public key modulus
	public BigInteger pub_modulus;

	// Public key modulus
	public BigInteger pub_exponent;

	// Key Factory for specifying the algorithm name for generating keys
	public KeyFactory keyFactory;

	// Cipher class provides the functionality of a cryptographic cipher for
	// encryption and decryption
	public Cipher cipher;

	// Constructor to initialize the keyfactory and the cipher
	public RSAImpl() throws NoSuchAlgorithmException, NoSuchPaddingException {
		keyFactory = KeyFactory.getInstance("RSA");
		cipher = Cipher.getInstance("RSA");
	}

	// Parameterized constructor for initializing the private key
	// This will be used during the read operation

	public RSAImpl(BigInteger pub_modulus, BigInteger pub_exponent)
			throws NoSuchAlgorithmException, NoSuchPaddingException {
		keyFactory = KeyFactory.getInstance("RSA");
		cipher = Cipher.getInstance("RSA");
		this.priv_modulus = pub_modulus;
		this.priv_exponent = pub_exponent;
	}

	
	/*
	 * function name: encrypt
	 * input: plain text
	 * output: cipher text
	 * methodology: Encrypts the input plain text using RSA algorithm
	 */
	
	public String encrypt(String plainText)
			throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, NoSuchPaddingException,
			UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(pub_modulus, pub_exponent);
		PublicKey pubKey = keyFactory.generatePublic(rsaPublicKeySpec);
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		byte[] data = plainText.getBytes("UTF8");
		byte[] encryptedData = cipher.doFinal(data);
		return new BigInteger(encryptedData).toString();
	}
	
	
	
	/*
	 * function name: generateNewKeys
	 * input: N/A
	 * output: N/A
	 * methodology: Generates the new set of keys (both the public and private keys) 
	 * and assigns them to the instance variables
	 */

	public void generateNewKeys() throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(1024);
		KeyPair keyPair = kpg.genKeyPair();

		RSAPublicKeySpec rsaPubKeySpec = keyFactory.getKeySpec(keyPair.getPublic(), RSAPublicKeySpec.class);
		RSAPrivateKeySpec rsaPrivKeySpec = keyFactory.getKeySpec(keyPair.getPrivate(), RSAPrivateKeySpec.class);

		pub_modulus = rsaPubKeySpec.getModulus();
		pub_exponent = rsaPubKeySpec.getPublicExponent();

		priv_modulus = rsaPrivKeySpec.getModulus();
		priv_exponent = rsaPrivKeySpec.getPrivateExponent();

	}
	
	
	
	/*
	 * function name: decrypt
	 * input: cipher text
	 * output: plain text
	 * methodology: Decrypt the input cipher text using RSA algorithm
	 */

	public String decrypt(String cipherText) throws InvalidKeySpecException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		String plainText;
		RSAPrivateKeySpec rsaPrivateKeySpec = new RSAPrivateKeySpec(priv_modulus, priv_exponent);
		PrivateKey privkey = keyFactory.generatePrivate(rsaPrivateKeySpec);
		cipher.init(Cipher.DECRYPT_MODE, privkey);

		byte[] ciphertextBytes = new BigInteger(cipherText).toByteArray();
		byte[] decryptedBytes = cipher.doFinal(ciphertextBytes);
		plainText = new String(decryptedBytes);

		return plainText;
		
	}
}
