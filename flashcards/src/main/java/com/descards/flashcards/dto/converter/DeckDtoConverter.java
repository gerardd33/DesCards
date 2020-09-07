package com.descards.flashcards.dto.converter;

import com.descards.flashcards.dto.DeckDto;
import com.descards.flashcards.model.Deck;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DeckDtoConverter {

	public DeckDto convertToDto(Deck deck) {
		return DeckDto.builder()
				.id(deck.getId())
				.name(deck.getName())
				.userId(deck.getUser().getId())
				.build();
	}
}
