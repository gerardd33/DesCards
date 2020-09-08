package com.descards.flashcards.dto;

import lombok.Data;

@Data
public class FlashcardPortionRequestDto {

	private Long offset;

	private Long limit;

	private String sortBy;

	private String direction;
}
