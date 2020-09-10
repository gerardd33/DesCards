package com.descards.flashcards.dto.converter;

import com.descards.flashcards.dto.DeckDto;
import com.descards.flashcards.model.entity.Deck;

public class DeckDtoConverter {

	public static DeckDto convertToDto(Deck deck) {
		return DeckDto.builder()
				.id(deck.getId())
				.name(deck.getName())
				.userId(deck.getUser().getId())
				.build();
	}

	public static Deck convertFromDto(DeckDto deckToCreateDto) {
		return new Deck(deckToCreateDto.getName());
	}
}
