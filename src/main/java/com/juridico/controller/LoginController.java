package com.juridico.controller;

import com.juridico.model.Usuario;
import com.juridico.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public String login(@RequestBody Usuario loginRequest) {
        try {
            // Faz autenticação
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getSenha()
                    )
            );
            // Se chegar aqui, login válid
            return "Login efetuado com sucesso!";
        } catch (AuthenticationException e) {
            return "Usuário ou senha inválidos!";
        }
    }
}

