package com.descards.flashcards.service.facade;

import com.descards.flashcards.api.dto.FlashcardDto;
import com.descards.flashcards.api.dto.FlashcardPortionRequestDto;
import com.descards.flashcards.api.dto.RepetitionIntervalUpdateRequestDto;
import com.descards.flashcards.model.nonentity.RepetitionIntervalUpdateRequest;

import java.util.List;
import java.util.Set;

public interface DeckFacade {

	List<FlashcardDto> getCardPortion(long deckId, FlashcardPortionRequestDto requestDto);

	void addCard(long deckId, FlashcardDto cardToAddDto);

	void addCards(long deckId, Set<FlashcardDto> cardsToAddDtos);

	void removeCard(long deckId, long cardToRemoveId);

	void removeCards(long deckId, Set<Long> cardsToRemoveIds);

	void updateCard(long deckId, FlashcardDto cardToUpdateDto);

	void updateCards(long deckId, Set<FlashcardDto> cardsToUpdateDtos);

	void updateInterval(long deckId, RepetitionIntervalUpdateRequestDto requestDto);

	void updateIntervals(long deckId, Set<RepetitionIntervalUpdateRequestDto> requestDtos);
}
