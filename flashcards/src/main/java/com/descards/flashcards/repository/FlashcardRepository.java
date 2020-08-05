package com.descards.flashcards.repository;

import com.descards.flashcards.model.Flashcard;
import org.springframework.data.repository.CrudRepository;

public interface FlashcardRepository extends CrudRepository<Flashcard, Long> {
}
