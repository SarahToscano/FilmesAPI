package br.com.netflix.inatel.projeto.controller.form;


import br.com.netflix.inatel.projeto.model.Avaliacao;
import br.com.netflix.inatel.projeto.model.Filme;
import br.com.netflix.inatel.projeto.model.Usuario;
import br.com.netflix.inatel.projeto.repository.AvaliacaoRepository;
import br.com.netflix.inatel.projeto.repository.FilmeRepository;
import br.com.netflix.inatel.projeto.repository.UsuarioRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AvaliacaoForm {
	
	
	@NotNull @NotEmpty
	private String titulo;
	
	@NotNull @NotEmpty
	private String descricao;
	
	
	
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
	
	
	public Avaliacao converter(Long userId, UsuarioRepository usuarioRepository) {
		Usuario user = usuarioRepository.getById(userId);
		return new Avaliacao(titulo, descricao, user);
	}
	
	
}
