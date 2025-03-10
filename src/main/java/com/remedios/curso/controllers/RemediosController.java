package com.remedios.curso.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.remedios.curso.remedio.DadosCadastroRemedio;
import com.remedios.curso.remedio.DadosDetalhamentoRemedio;
import com.remedios.curso.remedio.DadosListagemRemedio;
import com.remedios.curso.remedio.Remedio;
import com.remedios.curso.remedio.RemedioRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.remedios.curso.remedio.DadosAtualizarRemedio;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("remedios")
@Tag(name = "Remédios", description = "Gerencia o cadastro e manutenção de remédios")
@SecurityRequirement(name = "bearer-key")
public class RemediosController {

	@Autowired
	private RemedioRepository repository;
	
	@PostMapping
	@Transactional
	@Operation(summary = "Cadastrar um novo remédio", description = "Cria um novo registro de remédio no sistema.")
	public ResponseEntity<DadosDetalhamentoRemedio> cadastrar(@RequestBody @Valid DadosCadastroRemedio dados, UriComponentsBuilder uriBuilder) {
		var remedio = new Remedio(dados);
		repository.save(remedio);
		
		var uri = uriBuilder.path("/remedios/{id}").buildAndExpand(remedio.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhamentoRemedio(remedio));
	}
	
	@GetMapping
	@Operation(summary = "Listar remédios", description = "Retorna uma lista de todos os remédios ativos.")
	public ResponseEntity<List<DadosListagemRemedio>> listar(){
		var lista = repository.findAllByAtivoTrue().stream().map(DadosListagemRemedio::new).toList();
		
		return ResponseEntity.ok(lista);
	}
	
	@PutMapping
	@Transactional
	@Operation(summary = "Atualizar um remédio", description = "Atualiza os dados de um remédio existente.")
	public ResponseEntity<DadosDetalhamentoRemedio> atualizar(@RequestBody @Valid DadosAtualizarRemedio dados) {
		var remedio = repository.getReferenceById(dados.id());
		remedio.atualizarInformacoes(dados);
		
		return ResponseEntity.ok(new DadosDetalhamentoRemedio(remedio));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@Operation(summary = "Excluir um remédio", description = "Remove um remédio do banco de dados.")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		repository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("inativar/{id}")
	@Transactional
	@Operation(summary = "Inativar um remédio", description = "Marca um remédio como inativo, sem removê-lo do banco de dados.")
	public ResponseEntity<Void> inativar(@PathVariable Long id) {
		var remedio = repository.getReferenceById(id);
		remedio.inativar();
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("reativar/{id}")
	@Transactional
	@Operation(summary = "Reativar um remédio", description = "Ativa novamente um remédio que estava inativo.")
	public ResponseEntity<Void> reativar(@PathVariable Long id) {
		var remedio = repository.getReferenceById(id);
		remedio.reativarRemedio();
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Detalhar um remédio", description = "Retorna os detalhes de um remédio específico.")
	public ResponseEntity<DadosDetalhamentoRemedio> detalhar(@PathVariable Long id) {
		var remedio = repository.getReferenceById(id);
		
		return ResponseEntity.ok(new DadosDetalhamentoRemedio(remedio));
	}
}
