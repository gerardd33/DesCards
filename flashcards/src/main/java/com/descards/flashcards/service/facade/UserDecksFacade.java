package com.descards.flashcards.service.facade;

import com.descards.flashcards.api.dto.DeckDto;

import java.util.List;

public interface UserDecksFacade {

	List<DeckDto> getDeckList(long userId);

	void createDeck(long userId, DeckDto deckToCreateDto);

	void removeDeck(long userId, long deckToRemoveId);
}
