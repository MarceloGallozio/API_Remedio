package com.remedios.curso.endereco;

import com.remedios.curso.funcionario.Funcionario;
import com.remedios.curso.medico.Medico;
import com.remedios.curso.paciente.Paciente;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

@Table(name = "Endereco")
@Entity(name = "enderecos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Endereco {

	public Endereco(DadosCadastroEndereco dados) {
		this.estado = dados.estado();
		this.cep = dados.cep();
		this.cidade = dados.cidade();
		this.rua = dados.rua();
		this.numero = dados.numero();
		this.complemento = dados.complemento();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	private String cep;
	private String cidade;
	private String rua;
	private int numero;
	private String complemento;
	
	@OneToOne(mappedBy = "endereco")
	private Medico medico;
	
	@OneToOne(mappedBy = "endereco")
	private Paciente paciente;
	
	@OneToOne(mappedBy = "endereco")
	private Funcionario funcionario;
	
	public DadosCadastroEndereco dadosCadastroEndereco() {
        return new DadosCadastroEndereco(this.cep, this.cidade, this.rua, this.numero, this.complemento, this.estado);
    }
	
	public DadosListagemEndereco dadosListagemEndereco() {
        return new DadosListagemEndereco(this.id ,this.cep, this.cidade, this.rua, this.numero, this.complemento, this.estado);
    }
	
	public void atualizarInformacoes(DadosAtualizarEndereco dados) {
	    if (dados.cep() != null) {
	        this.cep = dados.cep();
	    }
	    if (dados.cidade() != null) {
	        this.cidade = dados.cidade();
	    }
	    if (dados.rua() != null) {
	        this.rua = dados.rua();
	    }
	    if (dados.numero() != 0) { // Verifica se o número foi fornecido
	        this.numero = dados.numero();
	    }
	    if (dados.complemento() != null) {
	        this.complemento = dados.complemento();
	    }
	}
}
