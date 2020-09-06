package com.descards.flashcards.facade;

import com.descards.flashcards.dto.FlashcardDto;

import java.util.Set;

public interface DeckFacade {

	Set<FlashcardDto> getCardPortion(long deckId);
}
