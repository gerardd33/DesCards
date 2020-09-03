package com.descards.flashcards.service;

import com.descards.flashcards.facade.DeckFacade;
import com.descards.flashcards.model.Deck;
import com.descards.flashcards.model.Flashcard;
import com.descards.flashcards.repository.DeckRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DeckFacadeImpl implements DeckFacade {

	DeckRepository deckRepository;

	// TODO export constants
	private final long FLASHCARD_PORTION_SIZE = 20;

	@Override
	public List<Flashcard> getFlashcardPortion(long deckId) {
		Deck deck = deckRepository.findById(deckId);
		return deck.getCards().stream().limit(FLASHCARD_PORTION_SIZE).collect(Collectors.toList());
	}
}
