package com.descards.flashcards.controller;

import com.descards.flashcards.dto.CategoryDto;
import com.descards.flashcards.facade.CategoryFacade;
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
	List<CategoryDto> getAllCategories() {
		return categoryFacade.getAllCategories();
	}

	@GetMapping("/category/{categoryId}")
	List<String> getSpecialFields(@PathVariable long categoryId) {
		return categoryFacade.getSpecialFields(categoryId);
	}
}
