package com.remedios.curso.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarEndereco(
		
		@NotBlank
		String cep,
		
		@NotBlank
		String cidade,
		
		@NotBlank
		String rua,
		
		@NotNull
		int numero,
		
		String complemento
		) {

}
