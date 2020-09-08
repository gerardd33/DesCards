package com.descards.flashcards.repository;

import com.descards.flashcards.model.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface DeckRepository extends JpaRepository<Deck, Long> {
}
