package com.remedios.curso.medico;

import java.util.List;
import java.util.stream.Collectors;

import com.remedios.curso.documento.DadosCadastroDocumento;
import com.remedios.curso.endereco.DadosCadastroEndereco;
import com.remedios.curso.telefone.DadosCadastroTelefone;
import com.remedios.curso.telefone.Telefone;

import jakarta.validation.constraints.NotNull;

public record DadosDetalhamentoMedico(
		
		@NotNull
		Long id,
		
		String nome,
		
		String crm,
		
		EspecializacaoMedica especializacaoMedica,
		
		DadosCadastroDocumento documento,
		
		DadosCadastroEndereco endereco,
	
		List<DadosCadastroTelefone> telefones,
		
		Boolean ativo
		) {

	public DadosDetalhamentoMedico(Medico medico) {
		this(medico.getId(), medico.getNome(), medico.getCrm(), medico.getEspecializacaoMedica(), 
				medico.getDocumento().dadosCadastroDocumento(),
				medico.getEndereco().dadosCadastroEndereco(), 
				medico.getTelefones().stream().map(Telefone::dadosCadastroTelefone).collect(Collectors.toList()), 
				medico.getAtivo());
	}
}
