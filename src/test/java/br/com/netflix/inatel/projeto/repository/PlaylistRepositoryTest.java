package br.com.netflix.inatel.projeto.repository;
import java.util.Optional;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.Test;

import br.com.netflix.inatel.projeto.model.Playlist;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PlaylistRepositoryTest {
	@Autowired
	private PlaylistRepository playRepository;
	
	@Test
	public void deveListarPorNome() {
		String nome = "hp";
		Playlist playlist = playRepository.findByNome(nome).get();
		Assert.assertEquals(playlist.getNome(), nome);
	}
	
	@Test
	public void naoDeveListarUsuarioPorEmail() {
		String nome = "naoFuiCadastrado@email.com";
		Optional<Playlist> optional = playRepository.findByNome(nome);
		Assert.assertTrue(optional.isEmpty());
	}

}
