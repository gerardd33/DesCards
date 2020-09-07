package com.descards.flashcards.controller;

import com.descards.flashcards.facade.CategoryFacade;
import com.descards.flashcards.model.ApplicationUser;
import com.descards.flashcards.model.Category;
import com.descards.flashcards.repository.ApplicationUserRepository;
import com.descards.flashcards.repository.CategoryRepository;
import com.descards.flashcards.util.HttpGuard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class CategoryController {

	@Autowired
	CategoryFacade categoryFacade;

	@Autowired
	CategoryRepository categoryRepository;

	@GetMapping("/categories")
	ResponseEntity<?> getAllCategories() {
		return HttpGuard.getResponse(() -> categoryFacade.getAllCategories());
	}

	@GetMapping("/category/{categoryId}")
	ResponseEntity<?> getSpecialFields(@PathVariable long categoryId) {
		return HttpGuard.getResponse(() -> categoryFacade.getSpecialFields(categoryId));
	}
}
