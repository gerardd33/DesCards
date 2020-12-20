package com.descards.flashcards.util.api.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.descards.flashcards.api.dto.FlashcardDto;
import com.descards.flashcards.model.entity.Flashcard;
import com.descards.flashcards.model.nonentity.BasicSpacedRepetitionAlgorithm;
import com.descards.flashcards.model.nonentity.FlashcardFactory;
import com.descards.flashcards.model.nonentity.IntervalFactory;
import com.descards.flashcards.resource.TestResource;
import org.junit.jupiter.api.Test;

class FlashcardDtoMapperTest {

  private static final TestResource TEST_RESOURCE = new TestResource();

  @Test
  public void testMapToDto() {
    // given
    Flashcard flashcard = TEST_RESOURCE.getFlashcardMocks().getRandom();

    // when
    FlashcardDto flashcardDto = FlashcardDtoMapper.mapToDto(flashcard);

    //then
    assertEquals(flashcard.getId(), flashcardDto.getId());
    assertEquals(flashcard.getFront(), flashcardDto.getFront());
    assertEquals(flashcard.getBack(), flashcardDto.getBack());
    assertEquals(RepetitionIntervalDtoMapper.mapToDto(flashcard.getInterval()),
        flashcardDto.getInterval());
    assertEquals(flashcard.getCreatedTime(), flashcardDto.getCreated());
    assertEquals(flashcard.getDeck().getId(), flashcardDto.getDeckId());
  }

  @Test
  public void testMapFromDto() {
    // given
    Flashcard flashcard = TEST_RESOURCE.getFlashcardMocks().getRandom();
    FlashcardDto flashcardDto = FlashcardDto.builder()
        .id(5L)
        .deckId(12L)
        .front(flashcard.getFront())
        .back(flashcard.getBack())
        .build();
    FlashcardDtoMapper flashcardDtoMapper = new FlashcardDtoMapper();
    flashcardDtoMapper.setFlashcardFactory(
        new FlashcardFactory(new IntervalFactory(new BasicSpacedRepetitionAlgorithm())));

    // when
    Flashcard mappedFlashcard = FlashcardDtoMapper.mapFromDto(flashcardDto);

    //then
    assertEquals(flashcard.getFront(), mappedFlashcard.getFront());
    assertEquals(flashcard.getBack(), mappedFlashcard.getBack());
  }
}