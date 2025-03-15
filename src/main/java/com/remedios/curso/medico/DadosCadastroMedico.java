package com.remedios.curso.medico;

import java.util.List;

import com.remedios.curso.documento.DadosCadastroDocumento;
import com.remedios.curso.endereco.DadosCadastroEndereco;
import com.remedios.curso.telefone.DadosCadastroTelefone;

import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroMedico(
		
		@NotBlank
		String nome,
		
		@NotBlank
		String crm,
		
		@Enumerated
		EspecializacaoMedica especializacaoMedica,
		
		@NotNull
        @Valid
		DadosCadastroDocumento documento,
		
		@NotNull
        @Valid
		DadosCadastroEndereco endereco,
		
		@NotNull
        @Valid
		List<DadosCadastroTelefone> telefones
		
		) {

}
