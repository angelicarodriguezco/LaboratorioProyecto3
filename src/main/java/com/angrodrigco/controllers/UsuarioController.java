package com.angrodrigco.controllers;

import com.angrodrigco.dto.UsuarioResponseDTO;
import com.angrodrigco.service.UsuarioService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('SUPER-ADMIN-ROLE')")
    public List<UsuarioResponseDTO> findAll() {
        return usuarioService.findAll();
    }
}