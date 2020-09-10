package com.descards.flashcards.service.facade;

import com.descards.flashcards.api.dto.CategoryDto;

import java.util.List;

public interface CategoryFacade {

	List<CategoryDto> getAllCategories();
}
