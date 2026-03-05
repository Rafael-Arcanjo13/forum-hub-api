create table topicos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    mensagem VARCHAR(255) NOT NULL,
    data_criacao DATE NOT NULL,
    status VARCHAR(100) NOT NULL,
    usuario_id BIGINT NOT NULL,
    curso_id BIGINT NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,

    PRIMARY KEY (id),

    CONSTRAINT fk_topico_usuario
            FOREIGN KEY (usuario_id)
            REFERENCES usuarios(id)
            ON DELETE CASCADE,

        CONSTRAINT fk_topico_curso
            FOREIGN KEY (curso_id)
            REFERENCES cursos(id)
            ON DELETE CASCADE
)