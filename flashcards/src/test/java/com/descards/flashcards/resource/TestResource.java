package com.descards.flashcards.resource;

import com.descards.flashcards.model.entity.ApplicationUser;
import com.descards.flashcards.model.entity.Category;
import com.descards.flashcards.model.entity.Deck;
import com.descards.flashcards.model.entity.Flashcard;
import com.descards.flashcards.model.nonentity.BasicSpacedRepetitionAlgorithm;
import com.descards.flashcards.model.nonentity.FlashcardFactory;
import com.descards.flashcards.model.nonentity.IntervalFactory;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.Setter;
import org.junit.rules.ExternalResource;

@Getter
@Setter
public class TestResource extends ExternalResource {

  private Deck deck;

  private Category category;

  private ApplicationUser user;

  private FlashcardMocksData flashcardMocksData;

  public TestResource() {
    createMockUser();
    createMockDeck();
    createMockCategory();
    createMockFlashcards();
  }

  private void createMockUser() {
    user = new ApplicationUser();
    user.setId(1L);
  }

  private void createMockDeck() {
    deck = new Deck("History deck", user);
    deck.setId(1L);
  }

  private void createMockCategory() {
    category = new Category("Historical event");
    category.setId(1L);
    category.setSpecialFields(Stream.of("Date", "Who").collect(Collectors.toList()));
  }

  private void createMockFlashcards() {
    FlashcardFactory flashcardFactory = new FlashcardFactory(
        new IntervalFactory(new BasicSpacedRepetitionAlgorithm()));

    Flashcard flashcard1 = flashcardFactory.getObject("Muslim invasion of Spain – date", "711");
    Flashcard flashcard2 = flashcardFactory.getObject("Building of the Suez Canal – date", "1859");
    Flashcard flashcard3 = flashcardFactory.getObject("American Civil War – date", "1861-1865");
    Flashcard flashcard4 = flashcardFactory.getObject("Conquest of Granada – date", "1492");
    Flashcard flashcard5 = flashcardFactory.getObject("Peace of Augsburg – date", "1555");

    flashcard1.getInterval().setCurrent(Duration.ofDays(4));
    flashcard2.getInterval().setCurrent(Duration.ofDays(14));
    flashcard3.getInterval().setCurrent(Duration.ofDays(59));
    flashcard4.getInterval().setCurrent(Duration.ofMinutes(25));
    flashcard5.getInterval().setCurrent(Duration.ofDays(243));

    flashcard1.setCreatedTime(LocalDateTime.now().minusMinutes(10));
    flashcard5.setCreatedTime(LocalDateTime.now().plusMinutes(10));
    flashcardMocksData.setLastAdded(flashcard5);
    flashcardMocksData.setFirstAdded(flashcard1);

    flashcardMocksData.setRandom(flashcardMocksData.getAll().stream().findAny().orElse(flashcard1));

    Set<Flashcard> allFlashcards = Stream
        .of(flashcard1, flashcard2, flashcard3, flashcard4, flashcard5)
        .collect(Collectors.toSet());
    flashcardMocksData.setAll(allFlashcards);

    addFlashcardsToDeck();
  }

  void addFlashcardsToDeck() {
    AtomicLong index = new AtomicLong();
    flashcardMocksData.getAll().forEach(card -> {
      card.setDeck(deck);
      card.setId(index.incrementAndGet());
    });
    deck.getCards().addAll(flashcardMocksData.getAll());
  }
}
