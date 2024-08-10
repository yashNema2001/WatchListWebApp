package com.example.watchlistApp.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class RatingService {
	private String apiUrl = "http://www.omdbapi.com/?apikey=25f4d560&t=";
	
	public String getMovieRating(String title) {
		try {
			// try to fetch the rating by calling ombd api
			RestTemplate  template = new RestTemplate();
			//it will store the json object get by calling omdb api
			ResponseEntity<ObjectNode> response = template.getForEntity(apiUrl + title, ObjectNode.class);
			ObjectNode jsonObject = response.getBody();
			
			return jsonObject.path("imdbRating").asText();
		} catch(Exception e){
			//user entered rating will be taken
			System.out.println("Either movie name not available or api is down " + e.getMessage());
		}
		return null;
	}
}
