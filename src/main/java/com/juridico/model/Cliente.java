package com.juridico.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String cpfCnpj;

    // Relacionar com advogado (Usuário)
    @ManyToOne
    @JoinColumn(name = "advogado_id")
    private Usuario advogado;
}
