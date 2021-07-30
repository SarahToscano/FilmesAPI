package br.com.netflix.inatel.projeto.controller;import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import br.com.netflix.inatel.projeto.config.externalAPI.APIServiceConfig;
import br.com.netflix.inatel.projeto.controller.dto.AvaliacoesDTO;
import br.com.netflix.inatel.projeto.controller.dto.FilmeDto;
import br.com.netflix.inatel.projeto.controller.dto.PlaylistDto;
import br.com.netflix.inatel.projeto.controller.form.PlaylistForm;
import br.com.netflix.inatel.projeto.controller.form.UpdatePlaylistForm;
import br.com.netflix.inatel.projeto.domain.FilmeApi;
import br.com.netflix.inatel.projeto.domain.FilmeDetalhes;
import br.com.netflix.inatel.projeto.domain.FilmeDetalhesAPI;
import br.com.netflix.inatel.projeto.model.Playlist;
import br.com.netflix.inatel.projeto.model.Usuario;
import br.com.netflix.inatel.projeto.model.Avaliacao;
import br.com.netflix.inatel.projeto.model.Filme;

import br.com.netflix.inatel.projeto.repository.FilmeRepository;
import br.com.netflix.inatel.projeto.repository.PlaylistRepository;
import br.com.netflix.inatel.projeto.repository.UsuarioRepository;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PlaylistRepository playlistRepository;
	
	@Autowired
	private FilmeRepository filmeRepository;
	
	@Autowired
	private APIServiceConfig api;
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<PlaylistDto> create(Authentication authentication, @RequestBody @Valid PlaylistForm form,
			UriComponentsBuilder uriBuilder) {


		Usuario authenticatedUser = (Usuario) authentication.getPrincipal();
		Usuario userLogged = usuarioRepository.getById(authenticatedUser.getId());
		form.setProprietario(userLogged);
		Playlist playlist = form.convertToPlaylist();
		
		for (String imdb_id : form.getFilmes()) {

			FilmeDetalhes filmeApi = api.SearchMoviesByImdbID(imdb_id);
			Filme filme = new Filme(filmeApi.getImdb_id(), filmeApi.getTitle(), filmeApi.getYear(), filmeApi.getRating(), playlist);
			playlist.addFilme(filme);
			filmeRepository.save(filme);
		}
		
		playlistRepository.save(playlist);
		URI uri = uriBuilder.path("/playlist/{id}").buildAndExpand(playlist.getId()).toUri();
		return ResponseEntity.created(uri).body(new PlaylistDto(playlist)); 
		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<PlaylistDto> lista(@PathVariable Long id) {
		Optional<Playlist> optional = playlistRepository.findById(id);
		if (optional.isPresent()) {
			Playlist movieList = optional.get();
			return ResponseEntity.ok(new PlaylistDto(movieList));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(Authentication authentication, @PathVariable("id") Long id) {

		Usuario authenticatedUser = (Usuario) authentication.getPrincipal();
		Long authenticatedUserId = authenticatedUser.getId();

		Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);

		if (optionalPlaylist.isPresent()) {

			if (authenticatedUserId != optionalPlaylist.get().getProprietario().getId()) {
				return ResponseEntity.status(403).build();
			}

			playlistRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.status(404).build();
	}
	
	@PutMapping("/{playlistId}/add")
	@Transactional
	public ResponseEntity<PlaylistDto> addMovie(Authentication authentication,@PathVariable Long playlistId,  @RequestBody @Valid UpdatePlaylistForm form) {
		Usuario authenticatedUser = (Usuario) authentication.getPrincipal();
		Long authenticatedUserId = authenticatedUser.getId();

		Optional<Playlist> optionalPlaylist = playlistRepository.findById(playlistId);
		

		if (optionalPlaylist.isPresent()) {
			if (authenticatedUserId != optionalPlaylist.get().getProprietario().getId()) {
				return ResponseEntity.status(403).build();
			}
			Playlist playlist = optionalPlaylist.get();
			System.out.println(form.getFilmes());
			for (String i : form.getFilmes()) {
				System.out.println("aaaaaaaaaaaaaaaa");
				FilmeDetalhes filmeApi = api.SearchMoviesByImdbID(i);
				Filme filme = new Filme(filmeApi.getImdb_id(), filmeApi.getTitle(), filmeApi.getYear(), filmeApi.getRating(), playlist);
				filmeRepository.save(filme);
			}
			
			return ResponseEntity.ok(new PlaylistDto(playlist));
		}
		return ResponseEntity.notFound().build();
	}

}
