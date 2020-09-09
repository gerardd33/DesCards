package com.descards.flashcards.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@Builder
public class FlashcardDto {

	private Long id;

	private String front;

	@EqualsAndHashCode.Exclude
	private String back;

	@EqualsAndHashCode.Exclude
	private Long interval;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	@EqualsAndHashCode.Exclude
	private LocalDateTime created;

	@EqualsAndHashCode.Exclude
	private Long deckId;
}
