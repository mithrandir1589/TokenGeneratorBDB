package co.com.bancodebogota.services;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenGenerator implements ITokenGenerator{

	@Autowired
	private KeyGenerator keyGenerator;
	
	
	@Override
	public String getToken(String username) {
		// TODO Auto-generated method stub
		return generateToken(username);
	}
	
	private String generateToken(String username) {
		String token = null;
		try {
			token = Jwts.builder()
					.setSubject(username)
					.setExpiration(new Date(System.currentTimeMillis()+ 600000))
					.signWith(SignatureAlgorithm.RS512, keyGenerator.getPrivateKey())
					.compact();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return token;
	}
	

}
