package com.aklc.psmpa.pojo;

public class CryptoPojo {

	private String id;
	private String signature;
	private String modulus;
	private String exponent;
	private String priv_modulus;
	private String priv_exponent;
	private String data;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getPriv_modulus() {
		return priv_modulus;
	}

	public void setPriv_modulus(String priv_modulus) {
		this.priv_modulus = priv_modulus;
	}

	public String getPriv_exponent() {
		return priv_exponent;
	}

	public void setPriv_exponent(String priv_exponent) {
		this.priv_exponent = priv_exponent;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getModulus() {
		return modulus;
	}

	public void setModulus(String modulus) {
		this.modulus = modulus;
	}

	public String getExponent() {
		return exponent;
	}

	public void setExponent(String exponent) {
		this.exponent = exponent;
	}

}
