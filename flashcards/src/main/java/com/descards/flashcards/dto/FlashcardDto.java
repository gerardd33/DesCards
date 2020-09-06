package com.descards.flashcards.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Duration;

@Data
@Builder
public class FlashcardDto {

	private Long id;

	private String front;

	private String back;

	private Duration interval;

	private Long deckId;
}
