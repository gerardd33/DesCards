package com.descards.flashcards.service;

import com.descards.flashcards.dto.CategoryDto;
import com.descards.flashcards.dto.converter.CategoryDtoConverter;
import com.descards.flashcards.facade.CategoryFacade;
import com.descards.flashcards.model.entity.Category;
import com.descards.flashcards.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class CategoryFacadeImpl implements CategoryFacade {

	Category dummyCategory;

	CategoryRepository categoryRepository;

	@Override
	public List<CategoryDto> getAllCategories() {
		return StreamSupport.stream(categoryRepository.findAll().spliterator(), false)
				.map(CategoryDtoConverter::convertToDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<String> getSpecialFields(long categoryId) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(NoSuchElementException::new);
		return category.getSpecialFields();
	}
}
