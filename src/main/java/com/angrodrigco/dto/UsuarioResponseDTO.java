package com.angrodrigco.dto;

import java.util.Set;

public record UsuarioResponseDTO (
        Long id,
        String username,
        Set<String> roles
) {
}