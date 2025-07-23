package com.juridico.controller;

import com.juridico.model.Cliente;
import com.juridico.model.Usuario;
import com.juridico.service.ClienteService;
import com.juridico.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private UsuarioService usuarioService;

    // Cadastrar cliente (somente para advogado logado)
    @PostMapping
    public Cliente cadastrarCliente(@RequestBody Cliente cliente, Authentication authentication) {
        Usuario advogado = usuarioService.buscarUsuarioPorEmail(authentication.getName());
        cliente.setAdvogado(advogado);
        return clienteService.cadastrarCliente(cliente);
    }

    // Buscar clientes por nome
    @GetMapping("/buscar-por-nome")
    public List<Cliente> buscarPorNome(@RequestParam String nome, Authentication authentication) {
        Usuario advogado = usuarioService.buscarUsuarioPorEmail(authentication.getName());
        return clienteService.buscarClientesPorNome(advogado, nome);
    }

    // Buscar clientes por CPF/CNPJ
    @GetMapping("/buscar-por-cpfcnpj")
    public List<Cliente> buscarPorCpfCnpj(@RequestParam String cpfCnpj, Authentication authentication) {
        Usuario advogado = usuarioService.buscarUsuarioPorEmail(authentication.getName());
        return clienteService.buscarClientesPorCpfCnpj(advogado, cpfCnpj);
    }
}
