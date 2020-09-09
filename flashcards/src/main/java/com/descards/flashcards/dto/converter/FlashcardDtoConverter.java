package com.descards.flashcards.dto.converter;

import com.descards.flashcards.dto.FlashcardDto;
import com.descards.flashcards.model.entity.Flashcard;
import lombok.experimental.UtilityClass;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@UtilityClass
public class FlashcardDtoConverter {

	public FlashcardDto convertToDto(Flashcard flashcard) {
		return FlashcardDto.builder()
				.id(flashcard.getId())
				.front(flashcard.getFront())
				.back(flashcard.getBack())
				.interval(flashcard.getRepetitionInterval().toMinutes())
				.created(flashcard.getCreatedTime())
				.deckId(flashcard.getDeck().getId())
				.build();
	}

	public Flashcard convertFromDto(FlashcardDto flashcardDto) {
		return Flashcard.builder()
				.front(flashcardDto.getFront())
				.back(flashcardDto.getBack())
				.repetitionInterval(Duration.ofMinutes(flashcardDto.getInterval()))
				.createdTime(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))
				.build();
	}
}
