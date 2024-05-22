CREATE TABLE IF NOT EXISTS bandas (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255),
    genero VARCHAR(255),
    fecha DATE,
    pais VARCHAR(255),
    activos BOOLEAN
);
