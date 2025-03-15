package com.remedios.curso.telefone;

public record DadosListagemTelefone(
		
		Long id,
		
		String numeroTelefone,
		
		String celular
		) {
	public DadosListagemTelefone(Telefone telefone) {
		this(telefone.getId(), telefone.getNumeroTelefone(), telefone.getCelular());
	}
}
