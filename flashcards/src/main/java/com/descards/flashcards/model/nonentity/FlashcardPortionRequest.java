package com.descards.flashcards.model.nonentity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlashcardPortionRequest {

	private Integer pageIndex;

	private Integer itemsPerPage;

	private SortingField sortBy;

	private SortingDirection sortingDirection;
}
