package com.remedios.curso.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CriptografiaSenha {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public DadosCadastroUsuario criptografarSenha(DadosCadastroUsuario dados) {
		
		var senhaCriptografada = passwordEncoder.encode(dados.senha());
		
		return new DadosCadastroUsuario(dados.login(), senhaCriptografada);
	}
}
