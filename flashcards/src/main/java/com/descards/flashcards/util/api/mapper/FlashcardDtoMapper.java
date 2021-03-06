package com.descards.flashcards.util.api.mapper;

import com.descards.flashcards.api.dto.FlashcardDto;
import com.descards.flashcards.model.entity.Flashcard;
import com.descards.flashcards.model.nonentity.FlashcardFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlashcardDtoMapper {

	private static FlashcardFactory flashcardFactory;

	@Autowired
	public void setFlashcardFactory(FlashcardFactory flashcardFactory) {
		FlashcardDtoMapper.flashcardFactory = flashcardFactory;
	}

	public static FlashcardDto mapToDto(Flashcard flashcard) {
		return FlashcardDto.builder()
				.id(flashcard.getId())
				.front(flashcard.getFront())
				.back(flashcard.getBack())
				.interval(RepetitionIntervalDtoMapper.mapToDto(flashcard.getInterval()))
				.created(flashcard.getCreatedTime())
				.deckId(flashcard.getDeck().getId())
				.build();
	}

	public static Flashcard mapFromDto(FlashcardDto flashcardDto) {
		return flashcardFactory.getObject(
				flashcardDto.getFront(), flashcardDto.getBack()
		);
	}
}
