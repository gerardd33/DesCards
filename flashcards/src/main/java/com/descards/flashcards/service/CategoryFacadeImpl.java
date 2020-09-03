package com.descards.flashcards.service;

import com.descards.flashcards.facade.CategoryFacade;
import com.descards.flashcards.model.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryFacadeImpl implements CategoryFacade {

	Category dummyCategory;

	@Override
	public List<Category> getAllCategories() {
		return Collections.singletonList(dummyCategory);
	}

	@Override
	public List<String> getSpecialFields(long categoryId) {
		return dummyCategory.getSpecialFields();
	}
}
