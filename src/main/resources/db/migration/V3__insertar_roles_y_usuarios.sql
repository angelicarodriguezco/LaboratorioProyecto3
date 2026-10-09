INSERT INTO roles (nombre) VALUES ('SUPER-ADMIN-ROLE');
INSERT INTO roles (nombre) VALUES ('USER');

INSERT INTO usuarios (username, password) VALUES
('admin', '$2a$10$U2QdD4dYmvgTzunogtwDb.6asDm7hkBdcM0sgNI09K83k86WpjmnO'),
('user', '$2a$10$pQztyoyl7pBS6gYVG/vnl.vv6vNfDdL0wWvM366FQVFlvw9w6gcj6');

INSERT INTO roles_usuarios (usuario_id, rol_id)
SELECT u.id, r.id FROM usuarios u, roles r
WHERE u.username = 'admin' AND r.nombre = 'SUPER-ADMIN-ROLE';

INSERT INTO roles_usuarios (usuario_id, rol_id)
SELECT u.id, r.id FROM usuarios u, roles r
WHERE u.username = 'user' AND r.nombre = 'USER';

