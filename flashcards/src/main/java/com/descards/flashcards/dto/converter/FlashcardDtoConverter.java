package com.descards.flashcards.dto.converter;

import com.descards.flashcards.dto.FlashcardDto;
import com.descards.flashcards.model.entity.Flashcard;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FlashcardDtoConverter {

	public FlashcardDto convertToDto(Flashcard flashcard) {
		return FlashcardDto.builder()
				.id(flashcard.getId())
				.front(flashcard.getFront())
				.back(flashcard.getBack())
				.interval(flashcard.getInterval().getCurrent().toMinutes())
				.created(flashcard.getCreatedTime())
				.deckId(flashcard.getDeck().getId())
				.build();
	}

	public Flashcard convertFromDto(FlashcardDto flashcardDto) {
		return new Flashcard(flashcardDto.getFront(), flashcardDto.getBack());
	}
}
