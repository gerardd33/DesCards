package com.descards.flashcards.controller;

import com.descards.flashcards.facade.CategoryFacade;
import com.descards.flashcards.util.HttpGuard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

	@Autowired
	CategoryFacade categoryFacade;

	@GetMapping("/categories")
	ResponseEntity<?> getAllCategories() {
		return HttpGuard.getResponse(() -> categoryFacade.getAllCategories());
	}

	@GetMapping("/category/{categoryId}")
	ResponseEntity<?> getSpecialFields(@PathVariable long categoryId) {
		return HttpGuard.getResponse(() -> categoryFacade.getSpecialFields(categoryId));
	}
}
