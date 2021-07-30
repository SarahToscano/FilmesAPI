package br.com.netflix.inatel.projeto.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.netflix.inatel.projeto.config.externalAPI.APIServiceConfig;
import br.com.netflix.inatel.projeto.controller.dto.AvaliacoesDTO;
import br.com.netflix.inatel.projeto.controller.form.AvaliacaoForm;
import br.com.netflix.inatel.projeto.domain.Filme;
import br.com.netflix.inatel.projeto.domain.FilmeApi;
import br.com.netflix.inatel.projeto.domain.FilmeDetalhes;
import br.com.netflix.inatel.projeto.domain.FilmeDetalhesAPI;
import br.com.netflix.inatel.projeto.model.Avaliacao;
import br.com.netflix.inatel.projeto.repository.FilmeRepository;
import br.com.netflix.inatel.projeto.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/pesquisar")
@Slf4j

public class ApiController {

	@Autowired
	private APIServiceConfig apiService;

	
	@GetMapping("/filmes/{title}")
	@Cacheable(value = "titulosDeFilmes")
	public ResponseEntity<List<Filme>> searchByTitle(@PathVariable(required = true) String title){
		List<Filme> filmes = apiService.SearchMoviesByTitle(title);		
		return ResponseEntity.ok().body(filmes);
		
	}
	
	@Cacheable(value = "infoDeFilmes")
	@GetMapping("/imdb/{imdbId}")
	public ResponseEntity<FilmeDetalhes> searchByImdb(@PathVariable(required = true) String imdbId){
		FilmeDetalhes filme = apiService.SearchMoviesByImdbID(imdbId);		
		return ResponseEntity.ok().body(filme);
		
	}
	
	
}

