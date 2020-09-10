package com.descards.flashcards.dto.converter;

import com.descards.flashcards.dto.RepetitionIntervalUpdateRequestDto;
import com.descards.flashcards.model.nonentity.FlashcardStrength;
import com.descards.flashcards.model.nonentity.RepetitionIntervalUpdateRequest;

public class RepetitionIntervalUpdateRequestDtoConverter {

	public static RepetitionIntervalUpdateRequest convertFromDto(RepetitionIntervalUpdateRequestDto requestDto) {
		FlashcardStrength strength = FlashcardStrength.parseApiName(requestDto.getStrength());
		return RepetitionIntervalUpdateRequest.builder()
				.cardId(requestDto.getId())
				.cardStrength(strength)
				.build();
	}
}
