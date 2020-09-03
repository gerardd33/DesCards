package com.descards.flashcards.facade;

import com.descards.flashcards.model.Category;

import java.util.List;

public interface CategoryFacade {

	List<Category> getAllCategories();

	List<String> getSpecialFields(long categoryId);
}
