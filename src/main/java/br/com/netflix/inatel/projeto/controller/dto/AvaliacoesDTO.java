package br.com.netflix.inatel.projeto.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.netflix.inatel.projeto.model.Avaliacao;

public class AvaliacoesDTO {

	private Long id;
	private String titulo;
	private String descricao;
	private String usuarioNome;
	
	public AvaliacoesDTO (Avaliacao avaliacao ) {
		this.id = avaliacao.getId();
		this.titulo = avaliacao.getTitulo();
		this.descricao = avaliacao.getDescricao();
		this.usuarioNome = avaliacao.getUsuario().getNome();
	}
	
	
	public String getUsuarioNome() {
		return usuarioNome;
	}


	public Long getId() {
		return id;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	


	public static Page<AvaliacoesDTO> converter(Page<Avaliacao> avaliacoes) {
		return avaliacoes.map(AvaliacoesDTO::new);
	}
}
