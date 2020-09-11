package com.descards.flashcards.util.mapper;

import com.descards.flashcards.api.dto.DeckInfoDto;
import com.descards.flashcards.model.nonentity.DeckInfo;

public class DeckInfoDtoMapper {

	public static DeckInfoDto convertToDto(DeckInfo deckInfo) {
		return DeckInfoDto.builder()
				.deck(DeckDtoMapper.convertToDto(deckInfo.getDeck()))
				.totalCards(deckInfo.getTotalCards())
				.smallestInterval(deckInfo.getSmallestInterval().getCurrent().toMinutes())
				.greatestInterval(deckInfo.getGreatestInterval().getCurrent().toMinutes())
				.lastAddition(deckInfo.getLastAddition())
				.build();
	}
}
