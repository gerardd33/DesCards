package com.descards.flashcards.api.dto;

import lombok.Data;

@Data
public class FlashcardPortionRequestDto {

	private Integer page;

	private Integer perPage;

	private String sortBy;

	private String direction;
}
