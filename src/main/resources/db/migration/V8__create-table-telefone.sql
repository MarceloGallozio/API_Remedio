CREATE TABLE telefone (
    id BIGINT NOT NULL AUTO_INCREMENT,
    numero_telefone VARCHAR(20) NOT NULL,
    celular VARCHAR(20),
    medico_id BIGINT,
    paciente_id BIGINT,
    funcionario_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (medico_id) REFERENCES medico(id),
    FOREIGN KEY (paciente_id) REFERENCES paciente(id),
    FOREIGN KEY (funcionario_id) REFERENCES funcionario(id)
);