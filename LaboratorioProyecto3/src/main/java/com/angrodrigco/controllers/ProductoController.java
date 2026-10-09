package com.angrodrigco.controllers;

import com.angrodrigco.dto.ProductoDTO;
import com.angrodrigco.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<ProductoDTO> findAll() {
        return productoService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ProductoDTO findById(@PathVariable Long id) {
        return productoService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('SUPER-ADMIN-ROLE')")
    public ResponseEntity<ProductoDTO> create(@RequestBody ProductoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.create(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SUPER-ADMIN-ROLE')")
    public ProductoDTO update(@PathVariable Long id, @RequestBody ProductoDTO dto) {
        return productoService.update(dto, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SUPER-ADMIN-ROLE')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
