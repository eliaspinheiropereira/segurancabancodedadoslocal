package com.github.eliaspinheiropereira.seguranca.service;

import com.github.eliaspinheiropereira.seguranca.controller.dto.BuscarUsuarioDTO;
import com.github.eliaspinheiropereira.seguranca.controller.dto.CadastrarUsuarioDTO;
import com.github.eliaspinheiropereira.seguranca.model.Usuario;
import com.github.eliaspinheiropereira.seguranca.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public void cadastrarUsuario(CadastrarUsuarioDTO cadastrarUsuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setEmail(cadastrarUsuarioDTO.email());
        usuario.setSenha(passwordEncoder.encode(cadastrarUsuarioDTO.senha()));
        usuario.setRole(cadastrarUsuarioDTO.role());

        this.usuarioRepository.save(usuario);
    }

    public Page<BuscarUsuarioDTO> buscarTodosUsuarios(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Usuario> usuarios = this.usuarioRepository.findAll(pageable);
        Page<BuscarUsuarioDTO> buscarUsuarioDTOS = usuarios.map(usuario -> new BuscarUsuarioDTO(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getRole()
        ));
        return buscarUsuarioDTOS;
    }

    public void atualizarUsuario(UUID id, CadastrarUsuarioDTO cadastrarUsuarioDTO) {
        Usuario usuario = this.usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("usuario não encontrado no banco de dados"));
        usuario.setId(id);
        usuario.setEmail(cadastrarUsuarioDTO.email());
        usuario.setSenha(passwordEncoder.encode(cadastrarUsuarioDTO.senha()));
        usuario.setRole(cadastrarUsuarioDTO.role());

        this.usuarioRepository.save(usuario);
    }

    public void deletarUsuario(UUID id) {
        Usuario usuario = this.usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("usuario não encontrado no banco de dados"));
        this.usuarioRepository.deleteById(id);
    }

    public Usuario buscarUsuarioPorEmail(String email){
        Usuario usuario = this.usuarioRepository.findByEmail(email);

        if(usuario == null){
            throw new IllegalArgumentException("usuario não encontrado no banco de dados");
        }

        return usuario;
    }
}
