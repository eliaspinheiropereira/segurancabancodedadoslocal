package com.github.eliaspinheiropereira.seguranca.controller;

import com.github.eliaspinheiropereira.seguranca.controller.dto.BuscarProdutoDTO;
import com.github.eliaspinheiropereira.seguranca.controller.dto.CadastrarProdutoDTO;
import com.github.eliaspinheiropereira.seguranca.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Produtos", description = "Endpoints para gerenciamento de produtos")
public class ProdutosController {

    private final ProdutoService produtoService;


    @PostMapping
    @Operation(summary = "Cadastrar um novo produto", description = "Endpoint para cadastrar um novo produto. Requer permissão de ADMIN.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Produto cadastrado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado - permissão insuficiente")
    })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> cadastrarProduto(
            @RequestBody CadastrarProdutoDTO cadastrarProdutoDTO
    ) {
        log.info("Post -> /produtos");
        this.produtoService.cadastrarProduto(cadastrarProdutoDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Buscar todos os produtos", description = "Endpoint para buscar todos os produtos com paginação. Requer permissão de ADMIN ou USUARIO.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produtos buscados com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado - permissão insuficiente")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'USUARIO')")
    public ResponseEntity<Page<BuscarProdutoDTO>> buscarTodosProdutos(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ) {
        log.info("Get -> /produtos?page={}&size={}", page, size);
        Page<BuscarProdutoDTO> buscarProdutoDTOS = this.produtoService.buscarTodosProdutos(page, size);
        return new ResponseEntity(buscarProdutoDTOS.getContent(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um produto", description = "Endpoint para atualizar um produto existente. Requer permissão de ADMIN.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado - permissão insuficiente"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> atualizarProduto(
            @PathVariable("id") UUID id,
            @RequestBody CadastrarProdutoDTO cadastrarProdutoDTO
    ) {
        log.info("Put -> /produtos/{}", id);
        this.produtoService.atualizarProdutos(id, cadastrarProdutoDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um produto", description = "Endpoint para deletar um produto existente. Requer permissão de ADMIN.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado - permissão insuficiente"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletarProduto(
            @PathVariable("id") UUID id
    ) {
        log.info("Delete -> /produtos/{}", id);
        this.produtoService.deletarProduto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
