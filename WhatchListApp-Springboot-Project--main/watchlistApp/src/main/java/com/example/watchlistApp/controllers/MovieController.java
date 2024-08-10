package com.example.watchlistApp.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.watchlistApp.entity.Movie;
import com.example.watchlistApp.services.DatabaseService;

import jakarta.validation.Valid;

@RestController
public class MovieController {

	@Autowired
	DatabaseService databaseService;

//	gives the watchlistItemForm.html page                   
	@GetMapping("/watchlistItemForm")
//	we need request parameter only when user want to update data. therefore we initially make required attribute of requestParam as false
	public ModelAndView showWatchlistForm(@RequestParam(required = false) Integer id) {
		String viewName = "watchlistItemForm";

		Map<String, Object> model = new HashMap<>();

		if (id == null) { // it means user want to add new movie
			model.put("watchlistItem", new Movie());
		} else { // it means user wants to update data
			model.put("watchlistItem", databaseService.getMovieById(id));
		}

		return new ModelAndView(viewName, model);
	}

	@PostMapping("/watchlistItemForm")
	public ModelAndView submitWatchlistForm(@Valid @ModelAttribute("watchlistItem") Movie movie,
			BindingResult bindingResult) {

		// validation
		if (bindingResult.hasErrors()) {
			return new ModelAndView("watchlistItemForm");
		}

		Integer id = movie.getId();

		if (id == null) {
			databaseService.create(movie); // saves the user entered data into database
		} else {
			databaseService.update(movie, id); // updates the data
		}

		RedirectView rd = new RedirectView();
		rd.setUrl("/watchlist"); // it will redirect the web page from watchlistItemForm to watchlist page

		return new ModelAndView(rd);
	}

	@GetMapping("/watchlist")
	public ModelAndView getWatchlist(@RequestParam(required = false) Integer id) {
		String viewName = "watchlist";

		Map<String, Object> model = new HashMap<>();

		if (id != null) {
			databaseService.delete(id); // deletes the data
		}

		List<Movie> movieList = databaseService.getAllMovies(); // gives the list of movies stored in watchlist table
		model.put("watchlistrows", movieList);
		model.put("numberOfMovies", movieList.size());

		return new ModelAndView(viewName, model);
	}
	

	@GetMapping("/Home")
	public ModelAndView getHomePage() {
		String viewName = "index";

		Map<String, Object> model = new HashMap<>();
		return new ModelAndView(viewName, model);

	}
}
