package com.descards.flashcards.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryDto {

	private Long id;

	private String name;

	private List<String> specialFields;
}
