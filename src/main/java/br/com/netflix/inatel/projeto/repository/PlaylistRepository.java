package br.com.netflix.inatel.projeto.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.netflix.inatel.projeto.model.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Long>{
	Optional<Playlist> findByNome(String title);
}
