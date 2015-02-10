package security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import logger.Log;

public class CryptographyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3193199957082894315L;

	public CryptographyException(IllegalBlockSizeException e) {
		Log.logError(e);
	}

	public CryptographyException(BadPaddingException e) {
		Log.logError(e);
	}

	public CryptographyException(String string, IOException e) {
		Log.logError(e);
	}

	public CryptographyException(String string, IllegalBlockSizeException e) {
		Log.logError(e);
	}

	public CryptographyException(UnsupportedEncodingException e) {
		Log.logError(e);
	}

	public CryptographyException(String string, BadPaddingException e) {
		Log.logError(e);
	}

	public CryptographyException(String string, InvalidKeyException e) {
		Log.logError(e);
	}

}
