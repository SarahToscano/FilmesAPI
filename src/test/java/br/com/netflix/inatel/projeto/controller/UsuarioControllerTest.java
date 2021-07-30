package br.com.netflix.inatel.projeto.controller;

import static org.hamcrest.CoreMatchers.containsString;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.net.URI;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UsuarioControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	
	@Test
	public void deveCadatrarUsuario() throws Exception {
		URI uri = new URI("/perfil");
		String json = "{"
				+ "\"nome\": \"sarah\", "
				+ "\"email\": \"teste@email.com\", "
				+ "\"senha\": \"12345\", "
				+ "\"biografia\": \"asdads asdasd\""
				+ "}";
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status().isCreated());
	}
	
	@Test
	public void naoDeveCadatrarUsuario() throws Exception {
		URI uri = new URI("/perfil");
		String json = "{"
				+ "\"nome\": \"sarah\", "
				+ "\"email\": \"teste@email.com\", "
				+ "\"senha\": \"12345\" "
				+ "}";
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status().isBadRequest());
	}


}
