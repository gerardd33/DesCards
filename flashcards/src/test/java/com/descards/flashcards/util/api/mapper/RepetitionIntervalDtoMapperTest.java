package com.descards.flashcards.util.api.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.descards.flashcards.api.dto.RepetitionIntervalDto;
import com.descards.flashcards.model.nonentity.RepetitionInterval;
import com.descards.flashcards.resource.TestResource;
import org.junit.jupiter.api.Test;

class RepetitionIntervalDtoMapperTest {

  private static final TestResource TEST_RESOURCE = new TestResource();

  @Test
  public void testMapToDto() {
    // given
    RepetitionInterval interval = TEST_RESOURCE.getFlashcardMocks()
        .getRandom().getInterval();

    // when
    RepetitionIntervalDto intervalDto = RepetitionIntervalDtoMapper.mapToDto(interval);

    //then
    assertEquals(interval.getCurrent().toMinutes(), intervalDto.getCurrent());
    assertEquals(interval.getAgain().toMinutes(), intervalDto.getAgain());
    assertEquals(interval.getHard().toMinutes(), intervalDto.getHard());
    assertEquals(interval.getOk().toMinutes(), intervalDto.getOk());
    assertEquals(interval.getEasy().toMinutes(), intervalDto.getEasy());
  }
}