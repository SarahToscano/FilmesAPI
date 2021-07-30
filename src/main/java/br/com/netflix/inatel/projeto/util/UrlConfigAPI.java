package br.com.netflix.inatel.projeto.util;

import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;


public class UrlConfigAPI {
	
	private static final String GET_IMDB_ID_BY_TITLE = "https://data-imdb1.p.rapidapi.com/movie/imdb_id/byTitle/";
	private static final String GET_MOVIE_BY_IMDB_ID= "https://data-imdb1.p.rapidapi.com/movie/id/";
	private static final String GET_UPCOMING_MOVIES = "https://data-imdb1.p.rapidapi.com/movie/order/upcoming/";
	
	

	public static String getGetImdbIdByTitle() {
		return GET_IMDB_ID_BY_TITLE;
	}


	public static String getGetMovieByImdbId() {
		return GET_MOVIE_BY_IMDB_ID;
	}


	public static String getGetUpcomingMovies() {
		return GET_UPCOMING_MOVIES;
	}


	public static String API_FindMoviesIdByTitle(String title) {
		String aux =  getSearchQuery(title, GET_IMDB_ID_BY_TITLE);
		return (aux+"/");
	}
	
	
	public static String API_FindMovieByImdbId(String imdbId) {
		String aux =  GET_MOVIE_BY_IMDB_ID + imdbId;
		System.out.println("GET_MOVIE_BY_IMDB_ID + imdbId"+aux);
		return (aux+"/");
	}
	
	public static String API_FindUpcomingMovies(String movieId) {
		return GET_UPCOMING_MOVIES;
	}
	
	private static String getSearchQuery(final String userIn, final String concat) {
		StringBuilder queryBuilder = new StringBuilder(concat);

		try {
			queryBuilder.append(URLEncoder.encode(userIn, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		System.out.println("queryBuilder.toString()   " + queryBuilder.toString());

		return queryBuilder.toString();
	}

}
