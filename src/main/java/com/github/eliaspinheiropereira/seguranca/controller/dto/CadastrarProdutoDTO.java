package com.github.eliaspinheiropereira.seguranca.controller.dto;

public record CadastrarProdutoDTO(
        String nome,
        String descricao,
        double preco
) {
}
