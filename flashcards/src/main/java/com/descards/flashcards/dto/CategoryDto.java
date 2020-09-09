package com.descards.flashcards.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CategoryDto {

	private Long id;

	@EqualsAndHashCode.Include
	private String name;

	private List<String> specialFields;
}
