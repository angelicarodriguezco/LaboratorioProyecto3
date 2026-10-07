package com.angrodrigco.dto;

import java.math.BigDecimal;

public record ProductoDTO (
        Long id,
        String nombre,
        String descripcion,
        BigDecimal precio,
        Integer cantidadStock,
        Long categoriaId,
        String categoriaNombre
){
}
