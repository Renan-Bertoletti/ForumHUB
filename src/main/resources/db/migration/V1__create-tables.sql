CREATE TABLE perfil (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE usuario (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE curso (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE topico (
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    mensagem VARCHAR(255) NOT NULL,
    data_criacao DATETIME NOT NULL,
    status VARCHAR(50) NOT NULL,
    autor_id BIGINT NOT NULL,
    curso_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_topico_usuario FOREIGN KEY (autor_id) REFERENCES usuario(id),
    CONSTRAINT fk_topico_curso FOREIGN KEY (curso_id) REFERENCES curso(id),
    CONSTRAINT uk_topico UNIQUE (titulo, mensagem)
);