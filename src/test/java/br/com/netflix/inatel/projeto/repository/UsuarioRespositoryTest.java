package br.com.netflix.inatel.projeto.repository;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.netflix.inatel.projeto.model.Usuario;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UsuarioRespositoryTest {

	@Autowired
	private UsuarioRepository userRepository;
	
	@Test
	public void deveListarUsuarioPorEmail() {
		String email = "karen@gmail.com";
		Usuario usuario = userRepository.findByEmail(email).get();
		Assert.assertEquals(usuario.getEmail(), email);
	}
	
	@Test
	public void naoDeveListarUsuarioPorEmail() {
		String email = "naoFuiCadastrado@email.com";
		Optional<Usuario> optional = userRepository.findByEmail(email);
		Assert.assertTrue(optional.isEmpty());
	}
}
