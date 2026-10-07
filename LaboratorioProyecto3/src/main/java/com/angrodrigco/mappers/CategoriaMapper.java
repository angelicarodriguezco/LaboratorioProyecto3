package com.angrodrigco.mappers;

import com.angrodrigco.dto.CategoriaDTO;
import com.angrodrigco.model.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper (componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoriaMapper {

    CategoriaDTO toDTO(Categoria categoria);
    List<CategoriaDTO> toDTOList(List<Categoria> categorias);
    Categoria toEntity(CategoriaDTO dto);
}
