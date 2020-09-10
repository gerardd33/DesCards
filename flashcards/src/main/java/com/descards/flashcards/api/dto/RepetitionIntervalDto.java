package com.descards.flashcards.api.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RepetitionIntervalDto {

	@EqualsAndHashCode.Include
	private Long current;

	private Long again;

	private Long hard;

	private Long ok;

	private Long easy;
}
