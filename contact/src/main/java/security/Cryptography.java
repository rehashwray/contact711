package security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import admin.SystemConfigurations;

public class Cryptography {

	private static String skey = null;
	private static String ivx = null;

	static {
		initialize();
	}

	private static void initialize() {

		skey = SystemConfigurations.getSecurityKey();
		ivx = SystemConfigurations.getSecurityKey();
	}

	public static String encrypt(String data) throws CryptographyException,
			UnsupportedEncodingException {

		SecretKeySpec keySpec = new SecretKeySpec(skey.getBytes(), "AES");
		IvParameterSpec ivSpec = new IvParameterSpec(ivx.getBytes());

		Cipher cipher = getCypher(keySpec, ivSpec, Cipher.ENCRYPT_MODE);

		byte[] stringBytes;

		stringBytes = data.getBytes("UTF8");

		byte[] raw;
		try {
			raw = cipher.doFinal(stringBytes);
		} catch (IllegalBlockSizeException e) {
			throw new CryptographyException(e);
		} catch (BadPaddingException e) {
			throw new CryptographyException(e);
		}

		String base64 = DatatypeConverter.printBase64Binary(raw);

		return base64;
	}

	public static String decrypt(String encryptedData)
			throws CryptographyException {

		SecretKeySpec keySpec = new SecretKeySpec(skey.getBytes(), "AES");
		IvParameterSpec ivSpec = new IvParameterSpec(ivx.getBytes());

		Cipher cipher = getCypher(keySpec, ivSpec, Cipher.DECRYPT_MODE);

		byte[] raw;
		raw = DatatypeConverter.parseBase64Binary(encryptedData);

		byte[] stringBytes;
		try {
			stringBytes = cipher.doFinal(raw);
		} catch (IllegalBlockSizeException e) {
			throw new CryptographyException("Encrypted message was corrupted",
					e);
		} catch (BadPaddingException e) {
			throw new CryptographyException("Encrypted message was corrupted",
					e);
		}

		String decriptedData;
		try {
			decriptedData = new String(stringBytes, "UTF8");
		} catch (UnsupportedEncodingException e) {
			throw new CryptographyException(e);
		}
		return decriptedData;
	}
	
	private static Cipher getCypher(SecretKeySpec keySpec,
			IvParameterSpec ivSpec, int mode) throws CryptographyException {
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("invalid algorithm", e);
		} catch (NoSuchPaddingException e) {
			throw new RuntimeException("invalid padding", e);
		}
		try {
			cipher.init(mode, keySpec, ivSpec);
		} catch (InvalidKeyException e) {
			throw new CryptographyException("invalid key", e);
		} catch (InvalidAlgorithmParameterException e) {
			throw new RuntimeException("invalid algorithm parameter.", e);
		}
		return cipher;
	}
}
