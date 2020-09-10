package com.descards.flashcards.api.converter;

import com.descards.flashcards.api.dto.DeckDto;
import com.descards.flashcards.model.entity.Deck;

public class DeckDtoMapper {

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
