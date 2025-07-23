package com.juridico;


import com.juridico.controller.LoginController;
import com.juridico.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class LoginControllerTest {

    private LoginController loginController;
    private AuthenticationManager authenticationManager;

    @BeforeEach
    public void setup() {
        authenticationManager = Mockito.mock(AuthenticationManager.class);
        loginController = new LoginController(authenticationManager);
    }

    @Test
    public void loginSucesso() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@exemplo.com");
        usuario.setSenha("senha123");

        // Simula autenticação sem exceção
        Mockito.doNothing().when(authenticationManager)
                .authenticate(any(UsernamePasswordAuthenticationToken.class));

        ResponseEntity<?> response = loginController.login(usuario);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Login bem‑sucedido!", response.getBody());
    }

    @Test
    public void loginFalha() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@exemplo.com");
        usuario.setSenha("senhaErrada");

        // Simula exceção na autenticação
        Mockito.doThrow(AuthenticationException.class)
                .when(authenticationManager)
                .authenticate(any(UsernamePasswordAuthenticationToken.class));

        ResponseEntity<?> response = loginController.login(usuario);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Credenciais inválidas", response.getBody());
    }
}
