package com.remedios.curso.medico;

import com.remedios.curso.endereco.DadosCadastroEndereco;

public record DadosDetalhamentoMedicoEndereco(
		
		 Long id, 
		 
		 DadosCadastroEndereco endereco 
		) {

	public DadosDetalhamentoMedicoEndereco(Medico medico) {
        this(medico.getId(), medico.getEndereco().dadosCadastroEndereco());
    }
}
