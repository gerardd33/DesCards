package com.descards.flashcards.dto.converter;

import com.descards.flashcards.dto.FlashcardDto;
import com.descards.flashcards.model.entity.Flashcard;
import com.descards.flashcards.model.nonentity.FlashcardFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlashcardDtoConverter {

	private static FlashcardFactory flashcardFactory;

	@Autowired
	public static void setFlashcardFactory(FlashcardFactory flashcardFactory) {
		FlashcardDtoConverter.flashcardFactory = flashcardFactory;
	}

	public static FlashcardDto convertToDto(Flashcard flashcard) {
		return FlashcardDto.builder()
				.id(flashcard.getId())
				.front(flashcard.getFront())
				.back(flashcard.getBack())
				.interval(RepetitionIntervalDtoConverter.convertToDto(flashcard.getInterval()))
				.created(flashcard.getCreatedTime())
				.deckId(flashcard.getDeck().getId())
				.build();
	}

	public static Flashcard convertFromDto(FlashcardDto flashcardDto) {
		return flashcardFactory.getObject(
				flashcardDto.getFront(), flashcardDto.getBack()
		);
	}
}
