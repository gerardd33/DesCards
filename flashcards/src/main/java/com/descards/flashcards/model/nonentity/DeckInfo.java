package com.descards.flashcards.model.nonentity;

import com.descards.flashcards.api.dto.DeckDto;
import com.descards.flashcards.model.entity.Deck;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class DeckInfo {

	private Deck deck;

	private Long totalCards;

	private RepetitionInterval smallestInterval;

	private RepetitionInterval greatestInterval;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime lastAddition;
}
