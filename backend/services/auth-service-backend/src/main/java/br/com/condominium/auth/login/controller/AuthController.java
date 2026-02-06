package br.com.condominium.auth.login.controller;

import br.com.condominium.auth.login.dto.LoginInput;
import br.com.condominium.auth.login.dto.LoginOutput;
import br.com.condominium.auth.login.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginOutput login(@RequestBody LoginInput input) {
        return authService.login(input);
    }
}
