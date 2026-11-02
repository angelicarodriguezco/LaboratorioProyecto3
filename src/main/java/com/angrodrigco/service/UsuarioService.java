package com.angrodrigco.service;

import com.angrodrigco.dto.UsuarioResponseDTO;
import com.angrodrigco.model.Rol;
import com.angrodrigco.model.Usuario;
import com.angrodrigco.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioResponseDTO> findAll() {
        return usuarioRepository.findAll().stream()
            .map(this::toResponse)
            .toList();
    }

    private UsuarioResponseDTO toResponse(Usuario usuario) {
        Set<String> roles = usuario.getRoles().stream()
            .map(Rol::getNombre)
            .collect(Collectors.toSet());
        return new UsuarioResponseDTO(usuario.getId(), usuario.getUsername(), roles);
    }
}