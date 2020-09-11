package com.descards.flashcards.util.api.mapper;

import com.descards.flashcards.api.dto.DeckDto;
import com.descards.flashcards.model.entity.Deck;

public class DeckDtoMapper {

	public static DeckDto mapToDto(Deck deck) {
		return DeckDto.builder()
				.id(deck.getId())
				.name(deck.getName())
				.userId(deck.getUser().getId())
				.build();
	}

	public static Deck mapFromDto(DeckDto deckToCreateDto) {
		return new Deck(deckToCreateDto.getName());
	}
}
