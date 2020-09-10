package com.descards.flashcards.model.nonentity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlashcardPortionRequest {

	private Integer offset;

	private Integer limit;

	private SortingField sortBy;

	private SortingDirection sortingDirection;
}
