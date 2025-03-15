package com.remedios.curso.telefone;

import com.remedios.curso.funcionario.Funcionario;
import com.remedios.curso.medico.Medico;
import com.remedios.curso.paciente.Paciente;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Telefone")
@Entity(name = "telefones")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Telefone {
	
	public Telefone(DadosCadastroTelefone dados) {
		this.numeroTelefone = dados.numeroTelefone();
		this.celular = dados.celular();
	}
	
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String numeroTelefone;
	private String celular;
	
	@ManyToOne
	@JoinColumn(name = "medico_id")
	private Medico medico;
	
	@ManyToOne
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;
	
	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario;
	
	public DadosCadastroTelefone dadosCadastroTelefone() {
        return new DadosCadastroTelefone(this.numeroTelefone, this.celular);
    }
	
	public DadosListagemTelefone dadosListagemTelefone() {
        return new DadosListagemTelefone(this.id, this.numeroTelefone, this.celular);
    }
}
