package com.descards.flashcards.service;

import com.descards.flashcards.api.dto.CategoryDto;
import com.descards.flashcards.util.mapper.CategoryDtoMapper;
import com.descards.flashcards.service.facade.CategoryFacade;
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
				.map(CategoryDtoMapper::convertToDto)
				.collect(Collectors.toList());
	}
}
