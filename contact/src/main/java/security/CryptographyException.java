package security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public class CryptographyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3193199957082894315L;

	public CryptographyException(IllegalBlockSizeException e) {
		e.printStackTrace();
	}

	public CryptographyException(BadPaddingException e) {
		e.printStackTrace();
	}

	public CryptographyException(String string, IOException e) {
		e.printStackTrace();
	}

	public CryptographyException(String string, IllegalBlockSizeException e) {
		e.printStackTrace();
	}

	public CryptographyException(UnsupportedEncodingException e) {
		e.printStackTrace();
	}

	public CryptographyException(String string, BadPaddingException e) {
		e.printStackTrace();
	}

	public CryptographyException(String string, InvalidKeyException e) {
		e.printStackTrace();
	}

}
