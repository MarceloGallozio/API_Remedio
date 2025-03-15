package com.remedios.curso.medico;

import com.remedios.curso.endereco.DadosAtualizarEndereco;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarMedicoEndereco(
		
		@NotNull
		Long id,
		
		@Valid
		DadosAtualizarEndereco endereco
		) {
}
