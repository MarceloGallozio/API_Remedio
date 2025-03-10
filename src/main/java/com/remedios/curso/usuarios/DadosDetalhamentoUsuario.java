package com.remedios.curso.usuarios;

import jakarta.validation.constraints.NotNull;

public record DadosDetalhamentoUsuario(

		@NotNull Long id,

		String login,
		
		String senha) {
	
	public DadosDetalhamentoUsuario(Usuario usuario) {
		this(usuario.getId(), usuario.getLogin(), usuario.getSenha());
	}
}
