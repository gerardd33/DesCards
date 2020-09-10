package com.descards.flashcards.util.mapper;

import com.descards.flashcards.api.dto.CategoryDto;
import com.descards.flashcards.model.entity.Category;

public class CategoryDtoMapper {

	public static CategoryDto convertToDto(Category category) {
		return CategoryDto.builder()
				.id(category.getId())
				.name(category.getName())
				.specialFields(category.getSpecialFields())
				.build();
	}
}
