package com.juridico.service;

import com.juridico.model.Cliente;
import com.juridico.model.Usuario;
import com.juridico.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente cadastrarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> buscarClientesPorNome(Usuario advogado, String nome) {
        return clienteRepository.findByAdvogadoAndNomeContainingIgnoreCase(advogado, nome);
    }

    public List<Cliente> buscarClientesPorCpfCnpj(Usuario advogado, String cpfCnpj) {
        return clienteRepository.findByAdvogadoAndCpfCnpj(advogado, cpfCnpj);
    }
}

