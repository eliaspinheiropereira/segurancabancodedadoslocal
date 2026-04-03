package com.github.eliaspinheiropereira.seguranca.controller.dto;

import com.github.eliaspinheiropereira.seguranca.model.enums.Roles;

public record CadastrarUsuarioDTO(
        String email,
        String senha,
        Roles role
) {
}
