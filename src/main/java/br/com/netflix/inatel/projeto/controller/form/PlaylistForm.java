package br.com.netflix.inatel.projeto.controller.form;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.netflix.inatel.projeto.model.Playlist;
import br.com.netflix.inatel.projeto.model.Usuario;
import br.com.netflix.inatel.projeto.repository.UsuarioRepository;

public class PlaylistForm {

	@NotNull @NotEmpty
	private String nome;
	
	
	private List<String> filmes;
	
	private Usuario proprietario;
	
	

	public Usuario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Usuario proprietario) {
		this.proprietario = proprietario;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setFilmes(List<String> filmes) {
		this.filmes = filmes;
	}
	
	
	public String getNome() {
		return nome;
	}

	public List<String> getFilmes() {
		return filmes;
	}

	public Playlist convertToPlaylist() {
		return new Playlist(nome, proprietario);
	}

	
	
}
