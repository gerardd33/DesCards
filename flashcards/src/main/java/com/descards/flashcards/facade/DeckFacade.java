package com.descards.flashcards.facade;

import com.descards.flashcards.dto.FlashcardDto;
import com.descards.flashcards.dto.FlashcardPortionRequestDto;

import java.util.Set;

public interface DeckFacade {

	Set<FlashcardDto> getCardPortion(long deckId, FlashcardPortionRequestDto requestDto);
}
