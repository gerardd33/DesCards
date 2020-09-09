package com.descards.flashcards.service;

import com.descards.flashcards.dto.CategoryDto;
import com.descards.flashcards.test.converter.CategoryDtoConverter;
import com.descards.flashcards.facade.CategoryFacade;
import com.descards.flashcards.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class CategoryFacadeImpl implements CategoryFacade {

	CategoryRepository categoryRepository;

	@Override
	public List<CategoryDto> getAllCategories() {
		return StreamSupport.stream(categoryRepository.findAll().spliterator(), false)
				.map(CategoryDtoConverter::convertToDto)
				.collect(Collectors.toList());
	}
}
