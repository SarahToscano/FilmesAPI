package br.com.netflix.inatel.projeto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.netflix.inatel.projeto.model.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Long> {

	Filme findByTitle(String nomeFilme);
	

	
	

}
