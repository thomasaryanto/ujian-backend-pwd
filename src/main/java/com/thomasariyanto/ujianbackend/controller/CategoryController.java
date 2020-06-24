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
import com.thomasariyanto.ujianbackend.entity.Category;

@RestController
@RequestMapping("/categories")
@CrossOrigin
public class CategoryController {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@GetMapping
	public Iterable<Category> getCategories(){
		return categoryRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Category getCategoryById(@PathVariable int id) {
		return categoryRepo.findById(id).get();
	}
	
	@PostMapping
	public Category addCategory(@RequestBody Category category) {
		category.setId(0);
		return categoryRepo.save(category);
	}
	
	@PutMapping
	public Category updateCategory(@RequestBody Category category) {
		Category findCategory = categoryRepo.findById(category.getId()).get();
		
		findCategory.setCategoryName(category.getCategoryName());
		return categoryRepo.save(findCategory);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCategoryById(@PathVariable int id) {
		Category findCategory= categoryRepo.findById(id).get();
		
		findCategory.setMovies(null);
		categoryRepo.save(findCategory);
		categoryRepo.deleteById(id);
	}
}
