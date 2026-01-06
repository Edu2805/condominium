package br.com.condominium.auth.login.service;

import br.com.condominium.auth.credential.model.Credential;
import br.com.condominium.auth.credential.repository.CredentialRepository;
import br.com.condominium.auth.jwt.dto.LoginInput;
import br.com.condominium.auth.jwt.dto.LoginOutput;
import br.com.condominium.auth.jwt.service.JwtService;
import br.com.condominium.auth.user.model.User;
import br.com.condominium.auth.user.repository.UserRepository;
import br.com.condominium.auth.usertenant.model.UserTenant;
import br.com.condominium.auth.usertenant.repository.UserTenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final CredentialRepository credentialRepository;
    private final UserTenantRepository userTenantRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public LoginOutput login(LoginInput input) {

        User user = userRepository.findByEmail(input.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!user.isActive()) {
            throw new RuntimeException("Usuário inativo");
        }

        Credential credential = credentialRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("Credencial não encontrada"));

        if (!passwordEncoder.matches(input.password(), credential.getPasswordHash())) {
            throw new RuntimeException("Senha inválida");
        }

        UserTenant userTenant = userTenantRepository
                .findByUserIdAndTenantId(user.getId(), input.tenantId())
                .orElseThrow(() -> new RuntimeException("Usuário desconhecido"));

        String token = jwtService.generateToken(user, userTenant);

        return new LoginOutput(token);
    }
}
