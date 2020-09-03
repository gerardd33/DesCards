package com.descards.flashcards.facade;

import com.descards.flashcards.model.Flashcard;

import java.util.Set;

public interface DeckFacade {

	Set<Flashcard> getCardPortion(long deckId);
}
