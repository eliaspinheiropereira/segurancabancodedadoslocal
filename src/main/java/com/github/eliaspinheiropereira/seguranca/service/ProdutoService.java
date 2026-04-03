package com.github.eliaspinheiropereira.seguranca.service;

import com.github.eliaspinheiropereira.seguranca.repositories.ProdutoRepository;
import com.github.eliaspinheiropereira.seguranca.controller.dto.BuscarProdutoDTO;
import com.github.eliaspinheiropereira.seguranca.controller.dto.CadastrarProdutoDTO;
import com.github.eliaspinheiropereira.seguranca.model.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public void cadastrarProduto(CadastrarProdutoDTO cadastrarProdutoDTO) {
        Produto produto = new Produto();
        produto.setNome(cadastrarProdutoDTO.nome());
        produto.setDescricao(cadastrarProdutoDTO.descricao());
        produto.setPreco(cadastrarProdutoDTO.preco());

        this.produtoRepository.save(produto);
    }

    public Page<BuscarProdutoDTO> buscarTodosProdutos(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Produto> produtos = this.produtoRepository.findAll(pageable);
        Page<BuscarProdutoDTO> buscarProdutoDTOS = produtos.map(produto -> new BuscarProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco()
        ));
        return buscarProdutoDTOS;
    }

    public void atualizarProdutos(UUID id, CadastrarProdutoDTO cadastrarProdutoDTO) {
        Produto produto = this.produtoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("produto não cadastrado"));

        produto.setId(id);
        produto.setNome(cadastrarProdutoDTO.nome());
        produto.setDescricao(cadastrarProdutoDTO.descricao());
        produto.setPreco(cadastrarProdutoDTO.preco());

        this.produtoRepository.save(produto);
    }

    public void deletarProduto(UUID id) {
        Produto produto = this.produtoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("produto não cadastrado"));
        this.produtoRepository.deleteById(id);
    }
}
