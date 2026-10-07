package com.angrodrigco.mappers;

import com.angrodrigco.dto.ProductoDTO;
import com.angrodrigco.model.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper (componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductoMapper {

    @Mapping(source = "categoria.id", target = "categoriaId")
    @Mapping(source = "categoria.nombre", target = "categoriaNombre")
    ProductoDTO toDTO(Producto producto);

    List<ProductoDTO> toDTOList(List<Producto> productos);

    @Mapping(target = "categoria", ignore = true)
    Producto toEntity(ProductoDTO dto);
}
