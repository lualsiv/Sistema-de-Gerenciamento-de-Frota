package gerenciamentodefrota.infra;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

	private String password;

	public MD5(String password) {
		this.password = password;
	}

	private String hash() {
		MessageDigest md = null;
		
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		BigInteger hash = new BigInteger(1, md.digest(password.getBytes()));
		
		return hash.toString(16);
	}

	public String getPassword(){
		return this.hash();
	}
	
}
