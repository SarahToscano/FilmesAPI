package br.com.netflix.inatel.projeto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
public class Avaliacao {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String descricao;
	
	@ManyToOne
	private Usuario usuario;
	
	
	@ManyToOne
	private Filme filme;
	
	
	public Avaliacao() {
		
	}

	
	public Avaliacao(String titulo, String descricao, Usuario user) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = user;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(descricao, filme, id, titulo, usuario);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Avaliacao other = (Avaliacao) obj;
		return Objects.equals(descricao, other.descricao)
				&& Objects.equals(id, other.id) 
				&& Objects.equals(titulo, other.titulo) && Objects.equals(usuario, other.usuario);
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}




}
