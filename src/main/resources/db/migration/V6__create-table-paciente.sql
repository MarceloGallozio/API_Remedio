CREATE TABLE paciente (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    data_cadastro DATE NOT NULL,
    ativo BOOLEAN NOT NULL,
    documento_id BIGINT NOT NULL,
    endereco_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (documento_id) REFERENCES documento(id),
    FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);