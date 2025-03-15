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

import com.remedios.curso.medico.DadosAtualizarMedicoEndereco;
import com.remedios.curso.medico.DadosCadastroMedico;
import com.remedios.curso.medico.DadosDetalhamentoMedico;
import com.remedios.curso.medico.DadosDetalhamentoMedicoEndereco;
import com.remedios.curso.medico.DadosListagemMedico;
import com.remedios.curso.medico.DadosListagemMedicoCompleto;
import com.remedios.curso.medico.Medico;
import com.remedios.curso.medico.MedicoRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/medicos")
@Tag(name = "Médicos", description = "Gerencia o cadastro e manutenção de médicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

	@Autowired
	private MedicoRepository repository;
	
	@PostMapping
	@Transactional
	@Operation(summary = "Cadastrar um novo médico", description = "Cria um novo registro de médico no sistema.")
	public ResponseEntity<DadosDetalhamentoMedico> cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder){
		var medico = new Medico(dados);
		repository.save(medico);
		
		var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
	}
	
	@GetMapping
	@Operation(summary = "Listar médicos", description = "Retorna uma lista de todos os médicos ativos.")
	public ResponseEntity<List<DadosListagemMedico>> listar() {
		var lista = repository.findAllByAtivoTrue().stream().map(DadosListagemMedico::new).toList();
		
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Detalhar um médico", description = "Retorna os detalhes completos de um médico específico.")
	public ResponseEntity<DadosListagemMedicoCompleto> listarCompleto(@PathVariable Long id) {
		var medicoCompleto = repository.getReferenceById(id);
		
		return ResponseEntity.ok(new DadosListagemMedicoCompleto(medicoCompleto));
	}
	
	@PutMapping
	@Transactional
	@Operation(summary = "Atualizar o endereço de um médico", description = "Atualiza os dados do endereço de um médico existente.")
	public ResponseEntity<DadosDetalhamentoMedicoEndereco> atualizar(@RequestBody @Valid DadosAtualizarMedicoEndereco dados) {
		var medico = repository.getReferenceById(dados.id());
		medico.atualizarMedicoEndereco(dados);
		
		return ResponseEntity.ok(new DadosDetalhamentoMedicoEndereco(medico));
	}
	
	@DeleteMapping("inativar/{id}")
	@Transactional
	@Operation(summary = "Inativar um médico", description = "Marca um médico como inativo, sem removê-lo do banco de dados.")
	public ResponseEntity<Void> inativar(@PathVariable Long id) {
		var medico = repository.getReferenceById(id);
		medico.inativarMedico();
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("reativar/{id}")
	@Transactional
	@Operation(summary = "Reativar um médico", description = "Ativa novamente um médico que estava inativo.")
	public ResponseEntity<Void> reativar(@PathVariable Long id) {
		var medico = repository.getReferenceById(id);
		medico.reativarMedico();
		
		return ResponseEntity.noContent().build();
	}
}
