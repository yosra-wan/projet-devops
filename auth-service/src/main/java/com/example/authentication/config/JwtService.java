package com.example.authentication.config;

import com.example.authentication.model.STUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtService {

    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    public String generateToken(STUser user, Map<String, Object> extraClaims) {

        return generateToken(extraClaims, user);
    }


    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String email = extractEmail(token);
        return (email.equals(userDetails.getUsername())) && !isTokenExpired(token) && userDetails.isEnabled();
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public String generateToken(Map<String, Object> extraClaims, STUser user) {

        return Jwts.builder().
                setClaims(extraClaims).
                setSubject(user.getEmail()).
                setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis() + 30 * 24L * 60L * 60L * 1000L)). // 30 days in milliseconds
                        signWith(secretKey, SignatureAlgorithm.HS256).
                compact();

    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().
                setSigningKey(secretKey).
                build().parseClaimsJws(token).getBody();
    }


}
