package com.remedios.curso.telefone;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefoneRepository extends JpaRepository<Telefone, Long>{

	List<Telefone> findAll();
}
