CREATE TABLE categorias (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    descripcion VARCHAR(255)
);

CREATE TABLE productos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    descripcion VARCHAR(255),
    precio DECIMAL(10, 2),
    cantidad_stock INT,
    categoria_id BIGINT,
    FOREIGN KEY (categoria_id) REFERENCES categorias(id)
);