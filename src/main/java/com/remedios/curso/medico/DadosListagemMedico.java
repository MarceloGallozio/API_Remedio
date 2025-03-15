package com.remedios.curso.medico;

public record DadosListagemMedico(
		
		Long id,
		
		String nome,
		
		String crm
		) {
	public DadosListagemMedico(Medico medico) {
		this(medico.getId(), medico.getNome(), medico.getCrm());
	}
}
