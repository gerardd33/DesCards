package com.descards.flashcards.facade;

import com.descards.flashcards.dto.CategoryDto;

import java.util.List;

public interface CategoryFacade {

	List<CategoryDto> getAllCategories();

	List<String> getSpecialFields(long categoryId);
}
