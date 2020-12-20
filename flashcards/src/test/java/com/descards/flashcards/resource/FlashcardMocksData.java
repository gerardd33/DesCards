package com.descards.flashcards.resource;

import com.descards.flashcards.model.entity.Flashcard;
import java.util.Collection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FlashcardMocksData {

  private Collection<Flashcard> all;

  private Flashcard random;

  private Flashcard firstAdded;

  private Flashcard lastAdded;
}
