package com.juridico.repository;

import com.juridico.model.Cliente;
import com.juridico.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Buscar clientes do advogado pelo nome (contendo)
    List<Cliente> findByAdvogadoAndNomeContainingIgnoreCase(Usuario advogado, String nome);

    // Buscar clientes do advogado pelo CPF/CNPJ exato
    List<Cliente> findByAdvogadoAndCpfCnpj(Usuario advogado, String cpfCnpj);
}
