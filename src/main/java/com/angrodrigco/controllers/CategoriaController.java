package com.angrodrigco.controllers;

import com.angrodrigco.dto.CategoriaDTO;
import com.angrodrigco.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final  CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<CategoriaDTO> findAll() {
        return categoriaService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public CategoriaDTO findById(@PathVariable Long id) {
        return categoriaService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('SUPER-ADMIN-ROLE')")
    public ResponseEntity<CategoriaDTO> create(@RequestBody CategoriaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.create(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SUPER-ADMIN-ROLE')")
    public CategoriaDTO update(@PathVariable Long id, @RequestBody CategoriaDTO dto) {
        return categoriaService.update(dto, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SUPER-ADMIN-ROLE')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
