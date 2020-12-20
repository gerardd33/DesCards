package generator.util.api.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import generator.api.dto.FlashcardDto;
import generator.model.Flashcard;
import org.junit.jupiter.api.Test;

class FlashcardDtoMapperTest {

  @Test
  public void testMapToDto() {
    // given
    Flashcard flashcard = Flashcard.builder()
        .front("some front")
        .back("some back")
        .deckId(14L)
        .build();

    // when
    FlashcardDto flashcardDto = FlashcardDtoMapper.mapToDto(flashcard);

    //then
    assertEquals(flashcard.getFront(), flashcardDto.getFront());
    assertEquals(flashcard.getBack(), flashcardDto.getBack());
  }
}