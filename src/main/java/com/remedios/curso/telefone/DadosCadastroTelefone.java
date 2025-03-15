package com.remedios.curso.telefone;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroTelefone(
		
		@NotBlank
		String numeroTelefone,
		
		String celular
		) {

}
