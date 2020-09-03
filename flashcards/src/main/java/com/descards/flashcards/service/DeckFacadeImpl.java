package com.descards.flashcards.service;

import com.descards.flashcards.facade.DeckFacade;
import com.descards.flashcards.model.Deck;
import com.descards.flashcards.model.Flashcard;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class DeckFacadeImpl implements DeckFacade {

	Deck dummyDeck;

	@Override
	public Set<Flashcard> getFlashcardPortion(long deckId) {
		return new HashSet<>();
	}
}
