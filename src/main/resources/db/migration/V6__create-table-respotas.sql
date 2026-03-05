create table repostas (
    id BIGINT NOT NULL AUTO_INCREMENT,
    mensagem VARCHAR(255) NOT NULL,
    data_criacao DATE NOT NULL,
    topico_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    solucao BOOLEAN NOT NULL DEFAULT FALSE,

    PRIMARY KEY (id),

    CONSTRAINT fk_resposta_topico
            FOREIGN KEY (topico_id)
            REFERENCES topicos(id)
            ON DELETE CASCADE,

        CONSTRAINT fk_resposta_usuario
            FOREIGN KEY (usuario_id)
            REFERENCES usuarios(id)
            ON DELETE CASCADE
)