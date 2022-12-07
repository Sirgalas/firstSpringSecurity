package ru.sergalas.FirstSecurity.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;
import ru.sergalas.FirstSecurity.entities.users.entities.User;

@Component
public class JWTUtil {

    @Value("${jwt_secret}")
    private String secret;

    public String generateToken (User user)
    {
        Date expire = Date.from(ZonedDateTime.now().plusMinutes(60).toInstant());
        return JWT.create().withSubject("User details")
                .withClaim("name",user.getUsername())
                .withIssuedAt(new Date())
                .withIssuer("sergalas")
                .withExpiresAt(expire)
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateToken(String token) throws JWTVerificationException
    {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User details")
                .withIssuer("sergalas")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("name").asString();
    }
}
