package com.descards.flashcards.facade;

import com.descards.flashcards.dto.FlashcardDto;
import com.descards.flashcards.dto.FlashcardPortionRequestDto;

import java.util.List;
import java.util.Set;

public interface DeckFacade {

	List<FlashcardDto> getCardPortion(long deckId, FlashcardPortionRequestDto requestDto);

	void addCard(long deckId, FlashcardDto cardToAddDto);

	void addCards(long deckId, Set<FlashcardDto> cardsToAddDtos);

	void removeCard(long deckId, long cardToRemoveId);

	void removeCards(long deckId, Set<Long> cardsToRemoveIds);
}
