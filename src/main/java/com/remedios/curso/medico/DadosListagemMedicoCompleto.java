package com.remedios.curso.medico;

import java.util.List;
import java.util.stream.Collectors;

import com.remedios.curso.documento.DadosListagemDocumento;
import com.remedios.curso.endereco.DadosListagemEndereco;
import com.remedios.curso.telefone.DadosListagemTelefone;
import com.remedios.curso.telefone.Telefone;

public record DadosListagemMedicoCompleto(
		
		Long id,
		
		String nome,
		
		String crm,
		
		EspecializacaoMedica especializacaoMedica,
		
		Boolean ativo,
		
		DadosListagemDocumento documento,
		
		DadosListagemEndereco endereco,
		
		List<DadosListagemTelefone> telefones
		
		
		) {
	public DadosListagemMedicoCompleto(Medico medico) {
		this(medico.getId(), medico.getNome(), medico.getCrm(), medico.getEspecializacaoMedica(), medico.getAtivo(), 
				medico.getDocumento().dadosListagemDocumento(), 
				medico.getEndereco().dadosListagemEndereco(), 
				medico.getTelefones().stream().map(Telefone::dadosListagemTelefone).collect(Collectors.toList())
				);
	}
}
