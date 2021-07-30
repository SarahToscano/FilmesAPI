package br.com.netflix.inatel.projeto.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FilmeDetalhes {
	
	private String imdb_id;
	private String title;
	private Long year;
	private Long popularity;
	private Double rating;
	
	public Double getRating() {
		return rating;
	}

	private String description;
	
	
	public String getImdb_id() {
		return imdb_id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Long getYear() {
		return year;
	}
	
	public Long getPopularity() {
		return popularity;
	}
	
	public String getDescription() {
		return description;
	}
	
	
	
		

}
