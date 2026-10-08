package com.angrodrigco.service;

import com.angrodrigco.dto.ProductoDTO;
import com.angrodrigco.mappers.ProductoMapper;
import com.angrodrigco.model.Categoria;
import com.angrodrigco.model.Producto;
import com.angrodrigco.repository.CategoriaRepository;
import com.angrodrigco.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProductoMapper productoMapper;

    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository, ProductoMapper productoMapper) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.productoMapper = productoMapper;
    }

    public List<ProductoDTO> findAll() {
        return productoMapper.toDTOList(productoRepository.findAll());
    }

    public ProductoDTO findById(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return productoMapper.toDTO(producto);
    }

    public ProductoDTO create(ProductoDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        Producto producto = productoMapper.toEntity(dto);
        producto.setId(null);
        producto.setCategoria(categoria);
        return productoMapper.toDTO(productoRepository.save(producto));
    }

    public ProductoDTO update(ProductoDTO dto, Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        producto.setNombre(dto.nombre());
        producto.setDescripcion(dto.descripcion());
        producto.setPrecio(dto.precio());
        producto.setCantidadStock(dto.cantidadStock());
        producto.setCategoria(categoria);
        return productoMapper.toDTO(productoRepository.save(producto));
    }

    public void delete(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado");
        }
        productoRepository.deleteById(id);
    }
}
