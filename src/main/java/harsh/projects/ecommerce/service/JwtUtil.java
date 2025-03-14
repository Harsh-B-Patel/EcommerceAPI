package harsh.projects.ecommerce.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

import harsh.projects.ecommerce.exception.TokenInValidException;

public class JwtUtil {

    private static final String ISSUER = "EcommerceBackendAPI";
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Generate a secret key
    private static final long EXPIRATION_TIME = 3600000; // 1 hour


    // Generate JWT
    public static String generateToken(String subject, String userName) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuer(ISSUER)
                .claim("user", userName )
                //.claim("scope", "product")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Validate JWT
    public static boolean validateToken(String token) throws TokenInValidException {
    	System.out.println("JwtUtil.validateToken: Validate Token");
        try {
            Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token);
    		System.out.println("JwtUtil.validateToken: Validate Token Success");
            return true;
        } catch (Exception e) {
    		System.out.println("JwtUtil.validateToken: Validate Token Failed");
        	throw new TokenInValidException("The Token sent in RequestHeader is invalid");
            //return false;
        }
    }
}
