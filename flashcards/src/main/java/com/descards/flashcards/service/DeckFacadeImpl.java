package com.descards.flashcards.service;

import com.descards.flashcards.facade.DeckFacade;
import com.descards.flashcards.model.Deck;
import com.descards.flashcards.model.Flashcard;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class DeckFacadeImpl implements DeckFacade {

	@Autowired
	Deck dummyDeck;

	@Override
	public Set<Flashcard> getFlashcardPortion(long deckId) {
//		System.out.println(dummyDeck.getCards());
//		return new HashSet<>();
		return dummyDeck.getCards();
	}
}
