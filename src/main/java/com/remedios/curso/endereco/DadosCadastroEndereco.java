package com.remedios.curso.endereco;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroEndereco(
		
		@NotBlank
		String cep,
		
		@NotBlank
		String cidade,
		
		@NotBlank
		String rua,
		
		@NotNull
		int numero,
		
		String complemento,
		
		@Enumerated
		Estado estado
		) {

}
