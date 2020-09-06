package com.descards.flashcards.service;

import com.descards.flashcards.dto.FlashcardDto;
import com.descards.flashcards.dto.converter.FlashcardDtoConverter;
import com.descards.flashcards.facade.DeckFacade;
import com.descards.flashcards.model.Deck;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DeckFacadeImpl implements DeckFacade {

	Deck dummyDeck;

	@Override
	public Set<FlashcardDto> getCardPortion(long deckId) {
		// TODO get only N = 20 cards
		return dummyDeck.getCards().stream()
			.map(FlashcardDtoConverter::convertToDto)
			.collect(Collectors.toSet());
	}
}
