package com.example.watchlistApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.watchlistApp.entity.Movie;
import com.example.watchlistApp.repositories.MovieRepo;

@Service
public class DatabaseService {

	@Autowired
	MovieRepo movieRepo;

	@Autowired
	RatingService ratingService;

	public void create(Movie movie) {
		String rating = ratingService.getMovieRating(movie.getTitle());

		if (rating != null && !rating.equals("N/A")) {
			movie.setRating(Float.parseFloat(rating));
		}
		

		float realRating = movie.getRating();

		// setting priority
		if (realRating >= 1.0 && realRating <= 3.0) {
			movie.setPriority("L");
		} else if (realRating >= 4.0 && realRating <= 7.0) {
			movie.setPriority("M");
		} else {
			movie.setPriority("H");
		}
		
		movieRepo.save(movie);

	}

	public List<Movie> getAllMovies() { // returns all the rows present in watchlist table
		return movieRepo.findAll(Sort.by(Sort.Direction.DESC, "rating"));
	}

	public Movie getMovieById(Integer id) { // fetch the movie from database using id
		return movieRepo.findById(id).get();
	}

	public void update(Movie movie, Integer id) {// updates the data

		Movie movieToBeUpdated = getMovieById(id);
		movieToBeUpdated.setTitle(movie.getTitle());
		movieToBeUpdated.setRating(movie.getRating());
		movieToBeUpdated.setComment(movie.getComment());
		movieToBeUpdated.setPriority(movie.getPriority());

		movieRepo.save(movieToBeUpdated);
	}

	public void delete(Integer id) {
		movieRepo.deleteById(id);
	}

}
