package com.descards.flashcards.dto.converter;

import com.descards.flashcards.dto.CategoryDto;
import com.descards.flashcards.model.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryDtoConverter {

	public CategoryDto convertToDto(Category category) {
		return CategoryDto.builder()
				.id(category.getId())
				.name(category.getName())
				.build();
	}
}
