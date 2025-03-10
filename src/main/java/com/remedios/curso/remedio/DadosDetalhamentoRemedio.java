package com.remedios.curso.remedio;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record DadosDetalhamentoRemedio(

		@NotNull Long id,

		String nome,

		Via via,

		String lote,

		int quantidade,

		LocalDate validade,

		Laboratorio laboratorio,

		Boolean ativo) {

	public DadosDetalhamentoRemedio(Remedio remedio) {
		this(remedio.getId(), remedio.getNome(), remedio.getVia(), remedio.getLote(), remedio.getQuantidade(),
				remedio.getValidade(), remedio.getLaboratorio(), remedio.getAtivo());
	}
}
