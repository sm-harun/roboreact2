package com.nas.robospring.configuration;
import com.nas.robospring.Exception.UserAPIException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

@Component
public class JwtTokenProvider {

//    @Value("${app.jwt.secret}") // Fetch secret from application properties
    private String jwtSecret = "Hakim124160"; // This should be kept secure and not hard-coded

//    @Value("${app.jwt-expiration-milliseconds}") // Fetch expiration time from application properties
    private int jwtExpirationInMs = 3600000; // Default 1 hour

    // Generate token
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(username) // The subject (username) of the JWT
                .setIssuedAt(currentDate) // Current date
                .setExpiration(expireDate) // Set expiration date
                .signWith(SignatureAlgorithm.HS512, jwtSecret) // Sign with the secret
                .compact();
    }

    // Get username from the token
    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret) // Use the same secret used to sign the token
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject(); // Return the subject (username)
    }

    // Validate JWT token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token); // Parse and validate the token
            return true; // If parsing is successful, token is valid
        } catch (SignatureException ex) {
            throw new UserAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            throw new UserAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new UserAPIException(HttpStatus.BAD_REQUEST, "Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new UserAPIException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new UserAPIException(HttpStatus.BAD_REQUEST, "JWT claims string is empty.");
        }
    }
}
/*

  String secret = "mySuperSecret@123"; // Your secret
        String encodedSecret = Base64.getEncoder().encodeToString(secret.getBytes());
        System.out.println("Base64 Encoded Secret: " + encodedSecret);


import com.nas.robospring.Exception.UserAPIException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Configuration
public class JwtTokenProvider {
    private  String jwtSecret = "Hakim124160"; // Use a secure key
    private int jwtExpirationInMs = 36000000; // 1 hour
 */
/*   @Value("${jwt-secret}")
    private String jwtSecret;
    @Value("${jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;*//*


    // generate token
    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationInMs);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
        return token;
    }

    // get username from the token
    public String getUsernameFromJWT(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    // validate JWT token
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex){
            throw new UserAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            throw new UserAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new UserAPIException(HttpStatus.BAD_REQUEST, "Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new UserAPIException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new UserAPIException(HttpStatus.BAD_REQUEST, "JWT claims string is empty.");
        }
    }

}
*/
