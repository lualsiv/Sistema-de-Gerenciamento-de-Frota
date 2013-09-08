package gerenciamentodefrota.infra;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

	public String hash(String password) {
		if (password == null)
			throw new IllegalArgumentException();
		
		MessageDigest md = null;
		
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		BigInteger hash = new BigInteger(1, md.digest(password.getBytes()));
		
		return hash.toString(16);
	}
	
}
