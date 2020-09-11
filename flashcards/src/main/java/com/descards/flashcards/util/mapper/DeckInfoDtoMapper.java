package com.descards.flashcards.util.mapper;

import com.descards.flashcards.api.dto.DeckInfoDto;
import com.descards.flashcards.model.nonentity.DeckInfo;

import java.time.LocalDateTime;

public class DeckInfoDtoMapper {

	public static DeckInfoDto convertToDto(DeckInfo deckInfo) {
		if (deckInfo.getTotalCards() == 0) {
			return DeckInfoDto.builder()
					.deck(DeckDtoMapper.convertToDto(deckInfo.getDeck()))
					.totalCards(0L)
					.smallestInterval(0L)
					.greatestInterval(0L)
					.lastAddition(LocalDateTime.now())
					.build();
		}

		return DeckInfoDto.builder()
				.deck(DeckDtoMapper.convertToDto(deckInfo.getDeck()))
				.totalCards(deckInfo.getTotalCards())
				.smallestInterval(deckInfo.getSmallestInterval().getCurrent().toMinutes())
				.greatestInterval(deckInfo.getGreatestInterval().getCurrent().toMinutes())
				.lastAddition(deckInfo.getLastAddition())
				.build();
	}
}
