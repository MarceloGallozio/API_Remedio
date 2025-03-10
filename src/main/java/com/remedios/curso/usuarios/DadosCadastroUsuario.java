package com.remedios.curso.usuarios;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(

		@NotBlank String login,

		@NotBlank String senha) {

}
