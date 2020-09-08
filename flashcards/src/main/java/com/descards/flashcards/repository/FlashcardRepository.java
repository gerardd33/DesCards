package com.descards.flashcards.repository;

import com.descards.flashcards.model.Flashcard;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {

	List<Flashcard> findAllByDeckId(long deckId, Pageable pageable);
}
