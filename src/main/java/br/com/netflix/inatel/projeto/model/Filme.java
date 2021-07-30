package br.com.netflix.inatel.projeto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.netflix.inatel.projeto.config.externalAPI.APIServiceConfig;

@Entity
public class Filme {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String imdb_id;
	private String title;
	private Long year;
	private Double rating;
	
	@ManyToOne
	 private Playlist playlist;
	 
	 public Filme() {}

	 public Filme(String imdb_id, String title,Long year, Double rating, Playlist playlist ) {
        this.imdb_id = imdb_id;
    	this.title = title;
        this.playlist = playlist;
        this.year = year;
        this.rating = rating;
    }

	@Override
	public int hashCode() {
		return Objects.hash(imdb_id, playlist, title);
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filme other = (Filme) obj;
		return Objects.equals(imdb_id, other.imdb_id) && Objects.equals(playlist, other.playlist)
				&& Objects.equals(title, other.title);
	}
	
	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getImdb_id() {
		return imdb_id;
	}

	public void setImdb_id(String imdb_id) {
		this.imdb_id = imdb_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Playlist getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}
	 
	 

	
	
	
	
	
	
	

}
