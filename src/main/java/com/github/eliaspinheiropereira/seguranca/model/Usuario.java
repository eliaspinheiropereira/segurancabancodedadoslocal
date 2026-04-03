package com.github.eliaspinheiropereira.seguranca.model;

import com.github.eliaspinheiropereira.seguranca.model.enums.Roles;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String email;
    private String senha;
    @Enumerated(EnumType.STRING)
    private Roles role;
}
