package com.descards.flashcards.model.nonentity;

import com.descards.flashcards.model.entity.Flashcard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
public class FlashcardFactory {

  private final IntervalFactory intervalFactory;

  public Flashcard getObject(String front, String back) {
    return new Flashcard(intervalFactory.getObject(), front, back);
  }
}
