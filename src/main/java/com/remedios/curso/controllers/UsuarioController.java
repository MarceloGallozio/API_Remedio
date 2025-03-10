package com.remedios.curso.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.remedios.curso.usuarios.CriptografiaSenha;
import com.remedios.curso.usuarios.DadosCadastroUsuario;
import com.remedios.curso.usuarios.DadosDetalhamentoUsuario;
import com.remedios.curso.usuarios.Usuario;
import com.remedios.curso.usuarios.UsuarioRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private CriptografiaSenha criptografiaSenha;
	
	@PostMapping
	@Transactional
	@Operation(summary = "Cadastrar um novo usuário", description = "Registra um usuário no sistema e retorna os detalhes do usuário cadastrado.")
	public ResponseEntity<DadosDetalhamentoUsuario> cadastrarUsuario(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder) {
		DadosCadastroUsuario dadosComSenhaCriptografada = criptografiaSenha.criptografarSenha(dados);
		
		var usuario = new Usuario(dadosComSenhaCriptografada);
		repository.save(usuario);
		
		var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
	}
}
