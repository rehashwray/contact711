package tutorial;

import static org.junit.Assert.assertTrue;

import javax.crypto.IllegalBlockSizeException;

import org.junit.Test;

public class Junit {

	private static boolean method() throws CryptographyException {

		String a = null;
		// a.length();
		if (a == null)
			throw new CryptographyException(new IllegalBlockSizeException(""));

		return true;
	}

	@Test
	public void test1() throws CryptographyException {

		// try {
		assertTrue(true);
		
		assertTrue(false);
	}
	
	@Test
	public void test2() throws CryptographyException {

		// try {
		assertTrue(true);

	}
}
