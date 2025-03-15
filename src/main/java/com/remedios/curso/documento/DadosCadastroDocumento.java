package com.remedios.curso.documento;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroDocumento(

		@NotBlank
		String cpf,

		@NotBlank
		String rg,

		@NotNull
		LocalDate dataNascimento

) {

}
