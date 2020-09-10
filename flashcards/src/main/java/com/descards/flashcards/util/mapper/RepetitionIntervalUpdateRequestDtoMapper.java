package com.descards.flashcards.util.mapper;

import com.descards.flashcards.api.dto.RepetitionIntervalUpdateRequestDto;
import com.descards.flashcards.model.nonentity.FlashcardStrength;
import com.descards.flashcards.model.nonentity.RepetitionIntervalUpdateRequest;

public class RepetitionIntervalUpdateRequestDtoMapper {

	public static RepetitionIntervalUpdateRequest convertFromDto(RepetitionIntervalUpdateRequestDto requestDto) {
		FlashcardStrength strength = FlashcardStrength.parseApiName(requestDto.getStrength());
		return RepetitionIntervalUpdateRequest.builder()
				.cardId(requestDto.getId())
				.cardStrength(strength)
				.build();
	}
}
