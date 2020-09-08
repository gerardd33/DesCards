package com.descards.flashcards.facade;

import com.descards.flashcards.dto.FlashcardDto;
import com.descards.flashcards.dto.FlashcardPortionRequestDto;

import java.util.List;
import java.util.Set;

public interface DeckFacade {

	List<FlashcardDto> getCardPortion(long deckId, FlashcardPortionRequestDto requestDto);
}
