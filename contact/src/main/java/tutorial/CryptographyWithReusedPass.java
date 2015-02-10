package tutorial;

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

public class CryptographyWithReusedPass {

	private static String encrypt(String message, String skey, String ivx)
			throws CryptographyException, UnsupportedEncodingException {

		SecretKeySpec keySpec = new SecretKeySpec(skey.getBytes(), "AES");
		IvParameterSpec ivSpec = new IvParameterSpec(ivx.getBytes());

		Cipher cipher = getCypher(keySpec, ivSpec, Cipher.ENCRYPT_MODE);

		// Gets the raw bytes to encrypt, UTF8 is needed for
		// having a standard character set
		byte[] stringBytes;

		stringBytes = message.getBytes("UTF8");

		// encrypt using the cypher
		byte[] raw;
		try {
			raw = cipher.doFinal(stringBytes);
		} catch (IllegalBlockSizeException e) {
			throw new CryptographyException(e);
		} catch (BadPaddingException e) {
			throw new CryptographyException(e);
		}

		// converts to base64 for easier display.
		String base64 = DatatypeConverter.printBase64Binary(raw);						
		
		return base64;
	}

	public static String decrypt(String encrypted, String skey, String ivx)
			throws CryptographyException {

		SecretKeySpec keySpec = new SecretKeySpec(skey.getBytes(), "AES");
		IvParameterSpec ivSpec = new IvParameterSpec(ivx.getBytes());

		Cipher cipher = getCypher(keySpec, ivSpec, Cipher.DECRYPT_MODE);

		byte[] raw;
		raw = DatatypeConverter.parseBase64Binary(encrypted);

		// decode the message
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

		// converts the decoded message to a String
		String clear;
		try {
			clear = new String(stringBytes, "UTF8");
		} catch (UnsupportedEncodingException e) {
			throw new CryptographyException(e);
		}
		return clear;
	}

	/**
	 * @param keySpec
	 * @param ivSpec
	 * @param mode
	 * @return
	 * @throws CryptographyException
	 */
	public static Cipher getCypher(SecretKeySpec keySpec,
			IvParameterSpec ivSpec, int mode) throws CryptographyException {
		// Get a cipher object.
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

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String[] myArgs = {"-1", "1234567890123456", "1234567890123456"};
		
		args = myArgs;
		
		String message;
		String key;
		String ivx;
		if (args.length == 3) {
			message = args[0];
			key = args[1];
			ivx = args[2];
		} else {
			System.out.println("usage: ");
			System.out
					.println("CryptographyWithReusedPass [message] [skey] [ivx]\n key and ivx must have length 16 ");
			return;
		}

		System.out.println("clear message: " + message);

		String encrypted;
		try {
			encrypted = encrypt(message, key, ivx);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return;
		} catch (CryptographyException e) {
			e.printStackTrace();
			return;
		}
		System.out.println("encrypted message: " + encrypted);

		String decrypted;
		try {
			decrypted = decrypt(encrypted, key, ivx);
		} catch (CryptographyException e) {
			e.printStackTrace();
			return;
		}
		System.out.println("decrypted message: " + decrypted);

	}
}
