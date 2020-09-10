package com.descards.flashcards.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RepetitionIntervalUpdateRequestDto {

	@EqualsAndHashCode.Include
	private Long id;

	private String strength;
}
