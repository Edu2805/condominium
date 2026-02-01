package br.com.condominium.security.jwt;

public interface JwtService {

    String generate(JwtPayload payload);

    JwtPayload parse(String token);

    boolean isValid(String token);
}
