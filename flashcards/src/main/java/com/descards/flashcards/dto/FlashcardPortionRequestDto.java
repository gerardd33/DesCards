package com.descards.flashcards.dto;

import lombok.Data;

@Data
public class FlashcardPortionRequestDto {

	private Integer offset;

	private Integer limit;

	private String sortBy;

	private String direction;
}
