package br.com.condominium.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

public class DefaultJwtService implements JwtService {

    private final SecretKey secretKey;

    public DefaultJwtService(String secret) {
        this.secretKey = Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );
    }

    @Override
    public String generate(JwtPayload payload) {
        return Jwts.builder()
                .claim(JwtClaims.USER_ID, payload.userId().toString())
                .claim(JwtClaims.TENANT_ID, payload.tenantId().toString())
                .claim(JwtClaims.ROLE, payload.role())
                .claim(JwtClaims.TOKEN_TYPE, payload.tokenType().name())
                .issuedAt(Date.from(payload.issuedAt()))
                .expiration(Date.from(payload.expiresAt()))
                .signWith(secretKey)
                .compact();
    }

    @Override
    public JwtPayload parse(String token) {
        var claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return new JwtPayload(
                UUID.fromString(claims.get(JwtClaims.USER_ID, String.class)),
                UUID.fromString(claims.get(JwtClaims.TENANT_ID, String.class)),
                claims.get(JwtClaims.ROLE, String.class),
                TokenType.valueOf(claims.get(JwtClaims.TOKEN_TYPE, String.class)),
                claims.getIssuedAt().toInstant(),
                claims.getExpiration().toInstant()
        );
    }

    @Override
    public boolean isValid(String token) {
        try {
            parse(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}

