package br.com.netflix.inatel.projeto.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.netflix.inatel.projeto.config.externalAPI.APIServiceConfig;

//@JsonIgnoreProperties(ignoreUnknown=true)
public class Filme {


	private String imdb_id;
	private String title;
	
	
	public String getTitle() {
		return title;
	}
	public String getImdb_id() {
		return imdb_id;
	}
	public void setImdb_id(String imdb_id) {
		this.imdb_id = imdb_id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
	
}
