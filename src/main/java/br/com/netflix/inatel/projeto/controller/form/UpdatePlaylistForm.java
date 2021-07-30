package br.com.netflix.inatel.projeto.controller.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.netflix.inatel.projeto.controller.dto.FilmeDto;
import br.com.netflix.inatel.projeto.model.Filme;
import br.com.netflix.inatel.projeto.model.Playlist;
import br.com.netflix.inatel.projeto.repository.PlaylistRepository;

public class UpdatePlaylistForm {

	@NotNull @NotEmpty
	private List<String> filmes;
	
	public List<String> getFilmes() {
		return filmes;
	}

	public void setFilmes(List<String> filmes) {
		this.filmes = filmes;
	}

}
