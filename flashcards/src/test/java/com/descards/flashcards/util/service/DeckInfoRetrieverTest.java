package com.descards.flashcards.util.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.descards.flashcards.model.nonentity.RepetitionInterval;
import com.descards.flashcards.resource.TestResource;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class DeckInfoRetrieverTest {

  private static final TestResource TEST_RESOURCE = new TestResource();

  @Test
  public void testGetSmallestInterval() {
    // given
    DeckInfoRetriever deckInfoRetriever = new DeckInfoRetriever();
    deckInfoRetriever.setDeck(TEST_RESOURCE.getDeck());

    // when
    RepetitionInterval smallestInterval = deckInfoRetriever.getSmallestInterval();

    // then
    long expectedMinutes = TEST_RESOURCE.getFlashcardMocks().getWithSmallestInterval()
        .getInterval().getCurrent().toMinutes();
    long actualMinutes = smallestInterval.getCurrent().toMinutes();
    assertEquals(expectedMinutes, actualMinutes);
  }

  @Test
  public void testGetGreatestInterval() {
    // given
    DeckInfoRetriever deckInfoRetriever = new DeckInfoRetriever();
    deckInfoRetriever.setDeck(TEST_RESOURCE.getDeck());

    // when
    RepetitionInterval greatestInterval = deckInfoRetriever.getGreatestInterval();

    // then
    long expectedMinutes = TEST_RESOURCE.getFlashcardMocks().getWithGreatestInterval()
        .getInterval().getCurrent().toMinutes();
    long actualMinutes = greatestInterval.getCurrent().toMinutes();
    assertEquals(expectedMinutes, actualMinutes);
  }

  @Test
  public void testGetLastAddition() {
    // given
    DeckInfoRetriever deckInfoRetriever = new DeckInfoRetriever();
    deckInfoRetriever.setDeck(TEST_RESOURCE.getDeck());

    // when
    LocalDateTime lastAdditionTime = deckInfoRetriever.getLastAddition();

    // then
    LocalDateTime expectedLastAdditionTime = TEST_RESOURCE.getFlashcardMocks().getLastAdded()
        .getCreatedTime();
    LocalDateTime expectedFirstAdditionTime = TEST_RESOURCE.getFlashcardMocks().getFirstAdded()
        .getCreatedTime();

    assertEquals(expectedLastAdditionTime, lastAdditionTime);
    assertNotEquals(expectedFirstAdditionTime, lastAdditionTime);
  }
}