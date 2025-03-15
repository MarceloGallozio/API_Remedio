package com.remedios.curso.medico;

import java.util.List;
import java.util.stream.Collectors;

import com.remedios.curso.documento.Documento;
import com.remedios.curso.endereco.Endereco;
import com.remedios.curso.telefone.Telefone;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Medico")
@Entity(name = "medicos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
	
	public Medico(DadosCadastroMedico dados) {
		this.ativo = true;
		this.nome = dados.nome();
		this.crm = dados.crm();
		this.especializacaoMedica = dados.especializacaoMedica();
		this.documento = new Documento(dados.documento());
		this.endereco = new Endereco(dados.endereco());
		this.telefones = dados.telefones().stream().map(DadosCadastroTelefone -> {
			Telefone telefone = new Telefone(DadosCadastroTelefone);
			telefone.setMedico(this);
			return telefone;
		}).collect(Collectors.toList());
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String crm;
	
	@Enumerated(EnumType.STRING)
	private EspecializacaoMedica especializacaoMedica;
	
	private Boolean ativo;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "documento_id", referencedColumnName = "id")
	private Documento documento;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;
	
	@OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
	private List<Telefone> telefones;
	
	public void atualizarMedicoEndereco(@Valid DadosAtualizarMedicoEndereco dados) {
		if (dados.endereco() != null) {
			this.endereco.atualizarInformacoes(dados.endereco());
		}
	}
	
	public void inativarMedico() {
		this.ativo = false;
	}
	
	public void reativarMedico() {
		this.ativo = true;
	}
}
