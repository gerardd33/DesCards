package com.descards.flashcards.api.controller;

import com.descards.flashcards.service.facade.CategoryFacade;
import com.descards.flashcards.util.controller.HttpGuard;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CategoryController {

	CategoryFacade categoryFacade;

	@GetMapping("/categories")
	ResponseEntity<?> getAllCategories() {
		return HttpGuard.getResponse(() -> categoryFacade.getAllCategories());
	}
}
