package com.remedios.curso.endereco;

public record DadosListagemEndereco(
		
		Long id,
		
		String cep,
		
		String cidade,
		
		String rua,
		
		int numero,
		
		String complemento,
		
		Estado estado
		) {

	public DadosListagemEndereco(Endereco endereco) {
		this(endereco.getId(), endereco.getCep(), endereco.getCidade(), endereco.getRua(), endereco.getNumero(), endereco.getComplemento(), endereco.getEstado());
	}
}
