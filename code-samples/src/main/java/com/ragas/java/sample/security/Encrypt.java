package com.ragas.java.sample.security;

import java.security.MessageDigest;

/**
 * 
 * @author Chandra Jagarlamudi
 *
 */
public class Encrypt {
	public static void main(final String[] args) {
		String password = "secret";
		String algorithm = "SHA";

		byte[] plainText = password.getBytes();

		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);

			md.reset();
			md.update(plainText);
			byte[] encodedPassword = md.digest();

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < encodedPassword.length; i++) {
				if ((encodedPassword[i] & 0xff) < 0x10) {
					sb.append("0");
				}

				sb.append(Long.toString(encodedPassword[i] & 0xff, 16));
			}

			System.out.println("Plain    : " + password);
			System.out.println("Encrypted: " + sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
