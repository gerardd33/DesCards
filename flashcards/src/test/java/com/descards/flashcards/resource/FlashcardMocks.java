package com.descards.flashcards.resource;

import com.descards.flashcards.model.entity.Flashcard;
import java.util.Collection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FlashcardMocks {

  private Collection<Flashcard> all;

  private Flashcard firstAdded;

  private Flashcard lastAdded;

  private Flashcard withSmallestInterval;

  private Flashcard withGreatestInterval;

  public Flashcard getRandom() {
    return all.stream().findAny().orElse(firstAdded);
  }
}
