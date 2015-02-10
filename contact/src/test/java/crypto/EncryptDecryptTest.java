package crypto;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.*;

import security.Cryptography;
import security.CryptographyException;

public class EncryptDecryptTest {

	@Test
	public void encryptTest() throws UnsupportedEncodingException, CryptographyException{
		
		String idUnencrypted = "44";
		
		String idEncrypted1 = null;
		String idEncrypted2 = null;

		idEncrypted1 = Cryptography.encrypt(idUnencrypted);
		idEncrypted2 = Cryptography.encrypt(idUnencrypted);

		//same encryption output for same input
		assertTrue(idEncrypted1.equalsIgnoreCase(idEncrypted2));
	}
	
	@Test
	public void decryptTest() throws UnsupportedEncodingException, CryptographyException{				
		
		String idUnencrypted = "44";
		
		String idEncrypted1 = Cryptography.encrypt(idUnencrypted);;		
		String idDecrypted = Cryptography.decrypt(idEncrypted1);
		
		assertTrue(idDecrypted.equalsIgnoreCase(idUnencrypted));
	}
}
