package com.descards.flashcards.repository;

import com.descards.flashcards.model.entity.Flashcard;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {

	List<Flashcard> findAllByDeckId(long deckId, Pageable pageable);
}
