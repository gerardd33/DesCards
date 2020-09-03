package com.descards.flashcards.facade;

import com.descards.flashcards.model.Flashcard;

import java.util.List;

public interface DeckFacade {

	List<Flashcard> getFlashcardPortion(long deckId);
}
