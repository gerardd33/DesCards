package com.descards.flashcards.repository;

import com.descards.flashcards.model.Deck;
import org.springframework.data.repository.CrudRepository;

public interface DeckRepository extends CrudRepository<Deck, Long> {

	Deck findById(long deckId);
}
