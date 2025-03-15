package com.remedios.curso.documento;

import java.time.LocalDate;

import com.remedios.curso.funcionario.Funcionario;
import com.remedios.curso.medico.Medico;
import com.remedios.curso.paciente.Paciente;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Documento")
@Entity(name = "documentos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Documento {

	public Documento(DadosCadastroDocumento dados) {
		this.cpf = dados.cpf();
		this.rg = dados.rg();
		this.dataNascimento = dados.dataNascimento();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String cpf;
	private String rg;
	private LocalDate dataNascimento;
	
	@OneToOne(mappedBy = "documento")
	private Medico medico;
	
	@OneToOne(mappedBy = "documento")
	private Paciente paciente;
	
	@OneToOne(mappedBy = "documento")
	private Funcionario funcionario;
	
	public DadosCadastroDocumento dadosCadastroDocumento(){
		return new DadosCadastroDocumento(this.cpf, this.rg, this.dataNascimento);
	}
	
	public DadosListagemDocumento dadosListagemDocumento(){
		return new DadosListagemDocumento(this.id ,this.cpf, this.rg, this.dataNascimento);
	}
}
