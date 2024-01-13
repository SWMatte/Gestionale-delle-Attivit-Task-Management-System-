package com.taskManagmentSystem.service;
import com.taskManagmentSystem.model.Authentication;
import com.taskManagmentSystem.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
@Service
@Slf4j
public class JwtService {

    private static final String SECRET_KEY = "3a583z4yGLajy9kamd9BVMUq6BIwnIVg5ncXk6z9dGJG5I7RTX6UDUCS4nOeK6vLKYmlcDZ+32kacp0Vj6PcwhELDSBza03lw8YVZm4QvyqoVp7x4BnC1a41yyY4Eys94M/k3p38p5jP0FhA6dlxoTAqEjMnWHZSA0RDU9kI6Xnms9/73ENALj45r3NDR/qOII4cG2V7sFv0L2URB/KOlkw91Cg4JkCJXSrFWoVuH2ZUolj8wte8VVpe0VnquXScR9dehR4J7llsz6S99WMoR5Y9/n0w92FIXg50fbuRFAnNg6ox9WbvJY/oclSKYBu92R1fkyaWyFlcpDAD/be60M9JSJU7BgySd6vGs9sDaYA=\n";

    public String extractStringId(String token) {
        return extractClaim(token,Claims::getSubject);
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(Authentication user){
        return generateToken(new HashMap<>(),user);
    }
    public String generateToken(Map<String, Objects> extraClaims, Authentication user){
        log.info("Start method generateToken in class: " + getClass());
        return String.valueOf(Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(String.valueOf(user.getIdUser().getIdUser()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 12*60*60*1000 ))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact());
    }

    public boolean isTokenValid(String token){
        try{
            log.info("Starting method isTokenValid in class: " +getClass());
            Jwts
                    .parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token);
            if (!isTokenExpired(token)){
                log.info("The token is valid!");
                return true;
            }
            log.error("The token is expired!");
            return false;
        } catch (SignatureException ex) {
            log.error("Invalid JWT Signature");
        } catch (MalformedJwtException ex) {
            log.error("MalformedJWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT exception");
        } catch (IllegalArgumentException ex) {
            log.error("Jwt claims string is empty");
        }
        return false;
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
