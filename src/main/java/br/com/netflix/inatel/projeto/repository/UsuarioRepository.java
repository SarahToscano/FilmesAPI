package br.com.netflix.inatel.projeto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.netflix.inatel.projeto.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Optional <Usuario> findByEmail (String email);

}
