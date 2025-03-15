package com.remedios.curso.documento;

import java.time.LocalDate;

public record DadosListagemDocumento(
		
		Long id,
		
		String cpf,

		String rg,

		LocalDate dataNascimento
		) {

	public DadosListagemDocumento(Documento documento) {
		this(documento.getId(), documento.getCpf(), documento.getRg(), documento.getDataNascimento());
	}
}
