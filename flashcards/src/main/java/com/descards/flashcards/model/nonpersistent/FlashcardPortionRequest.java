package com.descards.flashcards.model.nonpersistent;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlashcardPortionRequest {

	private Long offset;

	private Long limit;

	private SortingField sortBy;

	private SortingDirection sortingDirection;
}
