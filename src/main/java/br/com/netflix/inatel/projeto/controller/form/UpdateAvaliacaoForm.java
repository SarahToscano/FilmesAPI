package br.com.netflix.inatel.projeto.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.netflix.inatel.projeto.model.Avaliacao;
import br.com.netflix.inatel.projeto.model.Usuario;
import br.com.netflix.inatel.projeto.repository.AvaliacaoRepository;

public class UpdateAvaliacaoForm {

	@NotNull @NotEmpty
	private String descricao;	

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	

	public Avaliacao atualizar(Long id, AvaliacaoRepository avaliacaoRepository) {
		Avaliacao avaliacao = avaliacaoRepository.getById(id);
		avaliacao.setDescricao(this.descricao);
		return avaliacao;
	}
	
	
	
}
