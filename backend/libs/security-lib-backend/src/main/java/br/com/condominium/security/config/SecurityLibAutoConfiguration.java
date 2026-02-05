package br.com.condominium.security.config;

import br.com.condominium.security.jwt.DefaultJwtService;
import br.com.condominium.security.jwt.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityLibAutoConfiguration {

    @Bean
    public JwtService jwtService(
            @Value("${security.jwt.secret}") String secret,
            @Value("${security.jwt.expiration}") long expiration
    ) {
        return new DefaultJwtService(secret, expiration);
    }
}
