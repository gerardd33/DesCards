package generator.util.api.mapper;

import generator.api.dto.FlashcardDto;
import generator.model.Flashcard;

public class FlashcardDtoMapper {

	public static FlashcardDto mapToDto(Flashcard flashcard) {
		return FlashcardDto.builder()
				.front(flashcard.getFront())
				.back(flashcard.getBack())
				.build();
	}
}
