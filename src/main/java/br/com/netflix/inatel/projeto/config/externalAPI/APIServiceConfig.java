package br.com.netflix.inatel.projeto.config.externalAPI;


import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import br.com.netflix.inatel.projeto.domain.Filme;
import br.com.netflix.inatel.projeto.domain.FilmeApi;
import br.com.netflix.inatel.projeto.domain.FilmeDetalhes;
import br.com.netflix.inatel.projeto.domain.FilmeDetalhesAPI;
import br.com.netflix.inatel.projeto.util.UrlConfigAPI;
import lombok.extern.slf4j.Slf4j;



@Service("apiService")
@Slf4j
public class APIServiceConfig {
	
	
	private RestTemplate restTemplate;
	
	
	private static final String x_rapidapi_key_value = "6349c61f46msh3f0494ff4a0e32cp15dd83jsn101d56ae5c99";
    private static final String x_rapidapi_host_value = "data-imdb1.p.rapidapi.com";

 

    private HttpHeaders defaultHeaders;
  
	@Autowired
	public APIServiceConfig() {
		this.defaultHeaders = new HttpHeaders();
        this.defaultHeaders.set("x-rapidapi-key", x_rapidapi_key_value);
        this.defaultHeaders.set("x-rapidapi-host", x_rapidapi_host_value);
		this.restTemplate = new RestTemplate(); //	(new RestTemplateBuilder()).build()

	}
   
    
    public List<Filme> SearchMoviesByTitle(String title) {
		System.out.println("Searching Movies By Title");
        HttpEntity request = new HttpEntity(defaultHeaders);
        ResponseEntity<String> response = this.restTemplate.exchange(UrlConfigAPI.API_FindMoviesIdByTitle(title), HttpMethod.GET, request,
        		String.class, title);
        Gson g = new Gson();
        FilmeApi p =  g.fromJson(response.getBody(), FilmeApi.class);
        List<Filme> filmes = Arrays.asList(p.getResult());

        return filmes;
        
    }
    
    public FilmeDetalhes SearchMoviesByImdbID(String imdbId) {
		System.out.println("Searching Movies By imdbID");
        HttpEntity request = new HttpEntity(defaultHeaders);
        ResponseEntity<String> response = this.restTemplate.exchange(UrlConfigAPI.API_FindMovieByImdbId(imdbId), HttpMethod.GET, request,
        		String.class, imdbId);
        
        JSONObject json = new JSONObject(response.getBody());

        Iterator<String> keys = json.keys();
        String tituloFilme=keys.next(); 
        String value = json.optString(tituloFilme);
        Gson g = new Gson();
        FilmeDetalhes p =  g.fromJson(value, FilmeDetalhes.class);
        return p;
        
    }
    
    

	
	
	
	

}
