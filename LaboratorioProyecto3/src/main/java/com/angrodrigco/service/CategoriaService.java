package com.angrodrigco.service;

import com.angrodrigco.dto.CategoriaDTO;
import com.angrodrigco.mappers.CategoriaMapper;
import com.angrodrigco.model.Categoria;
import com.angrodrigco.repository.CategoriaRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaService(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    public List<CategoriaDTO> findAll() {
        return categoriaMapper.toDTOList(categoriaRepository.findAll());
    }

    public CategoriaDTO findById(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        return categoriaMapper.toDTO(categoria);
    }

    public CategoriaDTO create(CategoriaDTO dto) {
        Categoria categoria = categoriaMapper.toEntity(dto);
        categoria.setId(null);
        return categoriaMapper.toDTO(categoriaRepository.save(categoria));
    }

    public CategoriaDTO update(CategoriaDTO dto, Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        categoria.setNombre(dto.nombre());
        categoria.setDescripcion(dto.descripcion());
        return categoriaMapper.toDTO(categoriaRepository.save(categoria));
    }

    public void delete(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoria no encontrada");
        }
        categoriaRepository.deleteById(id);
    }
}
