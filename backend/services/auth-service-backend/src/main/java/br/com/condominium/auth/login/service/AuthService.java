package br.com.condominium.auth.login.service;

import br.com.condominium.auth.login.dto.LoginInput;
import br.com.condominium.auth.login.dto.LoginOutput;
import br.com.condominium.security.jwt.*;
import br.com.condominium.security.role.Role;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

    private final JwtService jwtService;

    public AuthService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public LoginOutput login(LoginInput input) {

        // provisório até ter banco/migrations
        Map<String, Object> claims = Map.of(
                JwtClaims.USER_ID, "mock-user-id",
                JwtClaims.TENANT_ID, input.tenantId().toString(),
                JwtClaims.ROLE, Role.ADMIN.name(),
                JwtClaims.TOKEN_TYPE, TokenType.ACCESS.name()
        );

        String token = jwtService.generateToken(
                input.email(),
                claims
        );

        return new LoginOutput(token);
    }
}
