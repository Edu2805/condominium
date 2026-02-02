package br.com.condominium.security.jwt;

import java.util.Map;

public interface JwtService {

    String generateToken(
            String subject,
            Map<String, Object> claims
    );

    boolean isTokenValid(String token);

    String extractSubject(String token);

    Map<String, Object> extractClaims(String token);
}
