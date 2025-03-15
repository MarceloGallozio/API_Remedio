CREATE TABLE funcionario (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cargo VARCHAR(50) NOT NULL,
    ativo BOOLEAN NOT NULL,
    documento_id BIGINT NOT NULL,
    endereco_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (documento_id) REFERENCES documento(id),
    FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);