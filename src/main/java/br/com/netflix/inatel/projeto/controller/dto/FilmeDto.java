package br.com.netflix.inatel.projeto.controller.dto;

import br.com.netflix.inatel.projeto.model.Filme;

public class FilmeDto {
	
	
	private Long id;
	private String imdb_id;
	private String title;
	private Long year;
	private Double rating;
	
	public FilmeDto(Filme filme) {
		this.id = filme.getId();
		this.imdb_id = filme.getImdb_id();
		this.title = filme.getTitle();
		this.year = filme.getYear();
		this.rating = filme.getRating();
	}

	public Long getId() {
		return id;
	}

	public String getImdb_id() {
		return imdb_id;
	}

	public String getTitle() {
		return title;
	}

	public Long getYear() {
		return year;
	}

	public Double getRating() {
		return rating;
	}

	
	
	

}
