package com.descards.flashcards.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FlashcardDto {

	private Long id;

	@EqualsAndHashCode.Include
	private Long deckId;

	@EqualsAndHashCode.Include
	private String front;

	private String back;

	private RepetitionIntervalDto interval;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime created;
}
