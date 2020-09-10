package com.descards.flashcards.converter;

import com.descards.flashcards.dto.FlashcardPortionRequestDto;
import com.descards.flashcards.model.nonentity.FlashcardPortionRequest;
import com.descards.flashcards.model.nonentity.SortingDirection;
import com.descards.flashcards.model.nonentity.SortingField;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FlashcardPortionRequestDtoConverter {

	public FlashcardPortionRequest convertFromDto(FlashcardPortionRequestDto requestDto) {
		SortingField sortBy = SortingField.INTERVAL;
		if (requestDto.getSortBy() != null) {
			switch (requestDto.getSortBy()) {
				case "interval":
					sortBy = SortingField.INTERVAL;
					break;
				case "created":
					sortBy = SortingField.CREATED;
					break;
				case "front":
					sortBy = SortingField.FRONT;
					break;
				case "back":
					sortBy = SortingField.BACK;
					break;
			}
		}

		SortingDirection sortingDirection = SortingDirection.ASCENDING;
		if (requestDto.getDirection() != null) {
			switch (requestDto.getDirection()) {
				case "asc":
					sortingDirection = SortingDirection.ASCENDING;
					break;
				case "desc":
					sortingDirection = SortingDirection.DESCENDING;
					break;
			}
		}

		return FlashcardPortionRequest.builder()
				.offset(requestDto.getOffset())
				.limit(requestDto.getLimit())
				.sortBy(sortBy)
				.sortingDirection(sortingDirection)
				.build();
	}
}
