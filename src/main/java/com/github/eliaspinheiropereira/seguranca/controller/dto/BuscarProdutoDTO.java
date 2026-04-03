package com.github.eliaspinheiropereira.seguranca.controller.dto;

import java.util.UUID;

public record BuscarProdutoDTO(
        UUID id,
        String nome,
        String descricao,
        double preco
) {
}
