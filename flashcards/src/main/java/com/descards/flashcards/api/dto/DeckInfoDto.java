package com.descards.flashcards.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class DeckInfoDto {

	private DeckDto deck;

	private Long totalCards;

	private Long smallestInterval;

	private Long greatestInterval;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime lastAddition;
}
