package com.descards.flashcards.repository;

import com.descards.flashcards.model.entity.Deck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeckRepository extends JpaRepository<Deck, Long> {
}
