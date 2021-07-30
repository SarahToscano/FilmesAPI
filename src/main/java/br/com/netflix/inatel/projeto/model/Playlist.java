package br.com.netflix.inatel.projeto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import br.com.netflix.inatel.projeto.model.Filme;

@Entity
public class Playlist {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	@OneToMany(mappedBy = "playlist", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @OnDelete(action = OnDeleteAction.CASCADE)
	private List<Filme> filmes = new ArrayList<>();
	
	@ManyToOne
    private Usuario proprietario;
	
	public Playlist() {}

    public Playlist(String nome, Usuario proprietario) {
        this.nome = nome;
        this.proprietario = proprietario;
    }

	@Override
	public int hashCode() {
		return Objects.hash(filmes, id, nome, proprietario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Playlist other = (Playlist) obj;
		return Objects.equals(filmes, other.filmes) && Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(proprietario, other.proprietario);
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public List<Filme> getFilmes() {
		return filmes;
	}

	public Usuario getProprietario() {
		return proprietario;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setFilmes(List<Filme> filmes) {
		this.filmes = filmes;
	}

	public void setProprietario(Usuario proprietario) {
		this.proprietario = proprietario;
	}
	
	public boolean playlistVerificaFilme(String imdbId) {
		return filmes.contains(imdbId);
	}
	public void addFilme(Filme filme) {
        filmes.add(filme);
    }
	

    
	
	
}
