package com.github.eliaspinheiropereira.seguranca.controller;

import com.github.eliaspinheiropereira.seguranca.controller.dto.BuscarUsuarioDTO;
import com.github.eliaspinheiropereira.seguranca.controller.dto.CadastrarUsuarioDTO;
import com.github.eliaspinheiropereira.seguranca.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Cadastrar um novo usuário", description = "Endpoint para cadastrar um novo usuário.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso")
    })
    public ResponseEntity<Void> cadastrarUsuario(
            @RequestBody CadastrarUsuarioDTO cadastrarUsuarioDTO
    ) {
        log.info("POST -> /usuario");
        this.usuarioService.cadastrarUsuario(cadastrarUsuarioDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Buscar todos os usuários", description = "Endpoint para buscar todos os usuários com paginação.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuários buscados com sucesso")
    })
    public ResponseEntity<Page<BuscarUsuarioDTO>> buscarTodosUsuarios(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ) {
        log.info("GET -> /usuario?page={}&size={}", page, size);
        Page<BuscarUsuarioDTO> buscarUsuarioDTOS = this.usuarioService.buscarTodosUsuarios(page, size);
        return new ResponseEntity(buscarUsuarioDTOS.getContent(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um usuário", description = "Endpoint para atualizar um usuário existente.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<Void> atualizarUsuario(
            @PathVariable("id") UUID id,
            @RequestBody CadastrarUsuarioDTO cadastrarUsuarioDTO
    ) {
        log.info("PUT -> /usuario/{}", id);
        this.usuarioService.atualizarUsuario(id, cadastrarUsuarioDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um usuário", description = "Endpoint para deletar um usuário existente.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<Void> deletarUsuario(
            @PathVariable("id") UUID id
    ){
        log.info("DELETE -> /usuario/{}", id);
        this.usuarioService.deletarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}