package com.github.eliaspinheiropereira.seguranca.controller.dto;

import com.github.eliaspinheiropereira.seguranca.model.enums.Roles;

import java.util.UUID;

public record BuscarUsuarioDTO(
        UUID id,
        String email,
        String senha,
        Roles role
) {
}
