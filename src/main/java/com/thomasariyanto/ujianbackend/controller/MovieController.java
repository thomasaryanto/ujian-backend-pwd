package com.thomasariyanto.ujianbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thomasariyanto.ujianbackend.dao.CategoryRepo;
import com.thomasariyanto.ujianbackend.dao.MovieRepo;
import com.thomasariyanto.ujianbackend.entity.Category;
import com.thomasariyanto.ujianbackend.entity.Movie;

@RestController
@RequestMapping("/movies")
@CrossOrigin
public class MovieController {
	
	@Autowired
	private MovieRepo movieRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@GetMapping
	public Iterable<Movie> getMovies(){
		return movieRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Movie getMovieById(@PathVariable int id) {
		return movieRepo.findById(id).get();
	}
	
	@PostMapping
	public Movie addMovie(@RequestBody Movie movie) {
		movie.setId(0);
		return movieRepo.save(movie);
	}

	@PutMapping
	public Movie updateMovies(@RequestBody Movie movie) {
		Movie findMovie = movieRepo.findById(movie.getId()).get();
		findMovie.setMovieName(movie.getMovieName());
		findMovie.setYearReleased(movie.getYearReleased());
		return movieRepo.save(findMovie);
	}
	
	@DeleteMapping("/{id}")
	public void deleteMovieById(@PathVariable int id) {
		movieRepo.findById(id).get();
		movieRepo.deleteById(id);
	}
	
	@PutMapping("{movieId}/addCategory/{categoryId}")
	public Movie addCategoryToMovie(@PathVariable int movieId, @PathVariable int categoryId) {
		Movie findMovie = movieRepo.findById(movieId).get();
		Category findCategory = categoryRepo.findById(categoryId).get();
		
		if(findMovie.getCategories().contains(findCategory)) {
			throw new RuntimeException("Movie already assigned to this category!");
		}
			
		findMovie.getCategories().add(findCategory);
		return movieRepo.save(findMovie);
	}
	
	@PutMapping("{movieId}/removeCategory/{categoryId}")
	public Movie removeCategoryFromMovie(@PathVariable int movieId, @PathVariable int categoryId) {
		Movie findMovie = movieRepo.findById(movieId).get();
		Category findCategory = categoryRepo.findById(categoryId).get();
		
		if(!findMovie.getCategories().contains(findCategory)) {
			throw new RuntimeException("Movie not have this category!");
		}
		
		findMovie.getCategories().remove(findCategory);
		return movieRepo.save(findMovie);
	}
}
