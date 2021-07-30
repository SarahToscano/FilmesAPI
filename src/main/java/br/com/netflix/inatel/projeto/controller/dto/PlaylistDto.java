package br.com.netflix.inatel.projeto.controller.dto;
import java.util.stream.Collectors;


import java.util.ArrayList;
import java.util.List;

import br.com.netflix.inatel.projeto.model.Filme;
import br.com.netflix.inatel.projeto.model.Playlist;
import br.com.netflix.inatel.projeto.model.Usuario;

public class PlaylistDto {
	
	private Long id;
	private String name;
	private UsuarioDto proprietario;
	private List<FilmeDto> filmes;
	
	public PlaylistDto(Playlist playlist) {
		this.id = playlist.getId();
		this.name = playlist.getNome();
		this.proprietario = new UsuarioDto(playlist.getProprietario());
		this.filmes = new ArrayList<>();
		
		for (Filme i : playlist.getFilmes()) {
			filmes.add(new FilmeDto(i));
		}
	}

	public Long getId() {
		return id;
	}

	public UsuarioDto getProprietario() {
		return proprietario;
	}

	public String getName() {
		return name;
	}

	public List<FilmeDto> getFilmes() {
		return filmes;
	}
	
	public boolean verificaCriador(Long id) {
		return this.proprietario.getId() == id;
	}
	
	public static List<PlaylistDto> convert(List<Playlist> playlists) {
		List<PlaylistDto> playlistDto = playlists.stream().map(PlaylistDto::new).collect(Collectors.toList());
		return playlistDto;
	}
	
	

}
