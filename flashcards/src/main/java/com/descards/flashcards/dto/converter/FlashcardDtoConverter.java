package com.descards.flashcards.dto.converter;

import com.descards.flashcards.dto.FlashcardDto;
import com.descards.flashcards.model.Flashcard;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FlashcardDtoConverter {

	public FlashcardDto convertToDto(Flashcard flashcard) {
		return FlashcardDto.builder()
				.id(flashcard.getId())
				.front(flashcard.getFront())
				.back(flashcard.getBack())
				.interval(flashcard.getRepetitionInterval())
				.created(flashcard.getCreatedDate())
				.deckId(flashcard.getDeck().getId())
				.build();
	}
}
