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
    assertEquals(25, smallestInterval.getCurrent().toMinutes());
  }

  @Test
  public void testGetGreatestInterval() {
    // given
    DeckInfoRetriever deckInfoRetriever = new DeckInfoRetriever();
    deckInfoRetriever.setDeck(TEST_RESOURCE.getDeck());

    // when
    RepetitionInterval smallestInterval = deckInfoRetriever.getGreatestInterval();

    // then
    assertEquals(243 * 24 * 60, smallestInterval.getCurrent().toMinutes());
  }

  @Test
  public void testGetLastAddition() {
    // given
    DeckInfoRetriever deckInfoRetriever = new DeckInfoRetriever();
    deckInfoRetriever.setDeck(TEST_RESOURCE.getDeck());

    // when
    LocalDateTime lastAdditionTime = deckInfoRetriever.getLastAddition();

    // then
    assertEquals(TEST_RESOURCE.getLastAddedFlashcard().getCreatedTime(), lastAdditionTime);
    assertNotEquals(lastAdditionTime, TEST_RESOURCE.getFirstAddedFlashcard().getCreatedTime());
  }
}