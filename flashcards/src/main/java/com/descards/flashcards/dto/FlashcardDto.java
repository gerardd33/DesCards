package com.descards.flashcards.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@Builder
public class FlashcardDto {

	private Long id;

	private String front;

	private String back;

	private Duration interval;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime created;

	private Long deckId;
}
