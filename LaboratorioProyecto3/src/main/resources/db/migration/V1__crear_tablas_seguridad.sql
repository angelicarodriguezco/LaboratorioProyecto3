CREATE TABLE roles (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     nombre VARCHAR(255) UNIQUE
);

CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE,
    password VARCHAR(255)
);

CREATE TABLE roles_usuarios (
    rol_id BIGINT,
    usuario_id BIGINT,
    PRIMARY KEY (usuario_id, rol_id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (rol_id) REFERENCES roles(id)
);