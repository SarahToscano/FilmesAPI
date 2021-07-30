package br.com.netflix.inatel.projeto.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.netflix.inatel.projeto.controller.dto.AvaliacoesDTO;
import br.com.netflix.inatel.projeto.controller.dto.UsuarioDto;
import br.com.netflix.inatel.projeto.controller.form.AvaliacaoForm;
import br.com.netflix.inatel.projeto.controller.form.UpdateAvaliacaoForm;
import br.com.netflix.inatel.projeto.controller.form.UsuarioFormUpdate;
import br.com.netflix.inatel.projeto.model.Avaliacao;
import br.com.netflix.inatel.projeto.model.Usuario;
import br.com.netflix.inatel.projeto.repository.AvaliacaoRepository;
import br.com.netflix.inatel.projeto.repository.FilmeRepository;
import br.com.netflix.inatel.projeto.repository.UsuarioRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;


@RestController
@RequestMapping("/avaliacoes")
public class AvaliacoesController {
	
	@Autowired
	private AvaliacaoRepository avaliacaoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private FilmeRepository filmeRepository;
	
	
	
	@PostMapping
	@Transactional
	@CacheEvict (value = "listaDeAvaliacoes", allEntries=true)
	public ResponseEntity<AvaliacoesDTO> cadastrar(Authentication authentication, @RequestBody @Valid AvaliacaoForm avaliacaoForm, UriComponentsBuilder uriBuilder) {
		Usuario authenticatedUser = (Usuario) authentication.getPrincipal();
		Long authenticatedUserId = authenticatedUser.getId();
		
		Optional<Usuario> optionalUser = usuarioRepository.findById(authenticatedUserId);
		
		if (optionalUser.isPresent()) {
			Avaliacao avaliacao = avaliacaoForm.converter(authenticatedUserId, usuarioRepository);
			avaliacaoRepository.save(avaliacao);
			URI uri = uriBuilder.path("/avaliacoes/{id}").buildAndExpand(avaliacao.getId()).toUri();
			return ResponseEntity.created(uri).body(new AvaliacoesDTO(avaliacao));
		}

		return ResponseEntity.notFound().build();
	
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeAvaliacoes", allEntries = true)
	public ResponseEntity<AvaliacoesDTO> update(Authentication authentication, @PathVariable Long id, @RequestBody @Valid UpdateAvaliacaoForm form) {
		Usuario authenticatedUser = (Usuario) authentication.getPrincipal();
		Long authenticatedUserId = authenticatedUser.getId();
		String authenticatedUserNam = authenticatedUser.getNome();
		
		
		Optional<Usuario> optionalUser = usuarioRepository.findById(authenticatedUserId);
		Optional<Avaliacao> optionalAvaliacao= avaliacaoRepository.findById(id);

		if (optionalUser.isPresent()) {
			if (authenticatedUserNam != optionalAvaliacao.get().getUsuario().getNome()) {
				return ResponseEntity.status(403).build();
			}
			Avaliacao avaliacao = form.atualizar(id, avaliacaoRepository);
			return ResponseEntity.ok(new AvaliacoesDTO(avaliacao));
		}
		return ResponseEntity.notFound().build();

		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeAvaliacoes", allEntries = true)
	public ResponseEntity<?> delete(Authentication authentication, @PathVariable Long id) {

		Usuario authenticatedUser = (Usuario) authentication.getPrincipal();
		Long authenticatedUserId = authenticatedUser.getId();
		String authenticatedUserNam = authenticatedUser.getNome();
		
		Optional<Usuario> optionalUser = usuarioRepository.findById(authenticatedUserId);
		Optional<Avaliacao> optionalAvaliacao= avaliacaoRepository.findById(id);

		if (optionalUser.isPresent()) {
			if (authenticatedUserNam != optionalAvaliacao.get().getUsuario().getNome()) {
				return ResponseEntity.status(403).build();
			}
			avaliacaoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.status(404).build();
	}
	
	@GetMapping("/{id}")
	@Cacheable(value = "listaDeAvaliacoes")
	public ResponseEntity<AvaliacoesDTO> list(@PathVariable("id") Long id) {

		Optional<Avaliacao> optionalPlaylist = avaliacaoRepository.findById(id);

		if (optionalPlaylist.isPresent()) {
			AvaliacoesDTO playlistDto = new AvaliacoesDTO(optionalPlaylist.get());
			return ResponseEntity.ok().body(playlistDto);
		}

		return ResponseEntity.notFound().build();
	}

	
}
