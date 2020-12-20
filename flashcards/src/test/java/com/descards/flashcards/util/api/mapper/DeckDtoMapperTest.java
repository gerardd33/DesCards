package com.descards.flashcards.util.api.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.descards.flashcards.api.dto.DeckDto;
import com.descards.flashcards.model.entity.Deck;
import com.descards.flashcards.resource.TestResource;
import org.junit.jupiter.api.Test;

class DeckDtoMapperTest {

  private static final TestResource TEST_RESOURCE = new TestResource();

  @Test
  public void testMapToDto() {
    // given
    Deck deck = TEST_RESOURCE.getDeck();

    // when
    DeckDto deckDto = DeckDtoMapper.mapToDto(deck);

    //then
    assertEquals(deck.getId(), deckDto.getId());
    assertEquals(deck.getName(), deckDto.getName());
    assertEquals(deck.getUser().getId(), deckDto.getUserId());
  }

  @Test
  public void testMapFromDto() {
    // given
    Deck deck = TEST_RESOURCE.getDeck();
    DeckDto deckDto = DeckDto.builder()
        .id(deck.getId())
        .name(deck.getName())
        .userId(deck.getUser().getId())
        .build();

    // when
    Deck mappedDeck = DeckDtoMapper.mapFromDto(deckDto);

    //then
    assertEquals(deck.getName(), mappedDeck.getName());
  }
}