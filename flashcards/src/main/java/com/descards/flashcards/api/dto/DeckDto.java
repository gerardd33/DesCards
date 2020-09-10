package com.descards.flashcards.api.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DeckDto {

	private Long id;

	@EqualsAndHashCode.Include
	private Long userId;

	@EqualsAndHashCode.Include
	private String name;
}
