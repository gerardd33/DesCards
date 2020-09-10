package com.descards.flashcards.api.converter;

import com.descards.flashcards.api.dto.FlashcardPortionRequestDto;
import com.descards.flashcards.model.nonentity.FlashcardPortionRequest;
import com.descards.flashcards.model.nonentity.SortingDirection;
import com.descards.flashcards.model.nonentity.SortingField;

public class FlashcardPortionRequestDtoMapper {

	public static FlashcardPortionRequest convertFromDto(FlashcardPortionRequestDto requestDto) {
		SortingField sortBy = SortingField.INTERVAL;
		if (requestDto.getSortBy() != null) {
			sortBy = SortingField.parseApiName(requestDto.getSortBy());
		}

		SortingDirection sortingDirection = SortingDirection.ASCENDING;
		if (requestDto.getDirection() != null) {
			sortingDirection = SortingDirection.parseApiName(requestDto.getDirection());
		}

		return FlashcardPortionRequest.builder()
				.offset(requestDto.getOffset())
				.limit(requestDto.getLimit())
				.sortBy(sortBy)
				.sortingDirection(sortingDirection)
				.build();
	}
}