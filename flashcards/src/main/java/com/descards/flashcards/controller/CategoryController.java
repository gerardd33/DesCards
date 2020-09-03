package com.descards.flashcards.controller;

import com.descards.flashcards.facade.CategoryFacade;
import com.descards.flashcards.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

	@Autowired
	CategoryFacade categoryFacade;

	@GetMapping("/categories")
	List<Category> getAllCategories() {
		return categoryFacade.getAllCategories();
	}

	@GetMapping("/category/{category_id}")
	List<String> getSpecialFields(@PathVariable long categoryId) {
		return categoryFacade.getSpecialFields(categoryId);
	}
}
