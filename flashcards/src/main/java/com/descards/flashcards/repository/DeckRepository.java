package com.descards.flashcards.repository;

import com.descards.flashcards.model.entity.Deck;
import org.springframework.data.repository.CrudRepository;

public interface DeckRepository extends CrudRepository<Deck, Long> {
}
