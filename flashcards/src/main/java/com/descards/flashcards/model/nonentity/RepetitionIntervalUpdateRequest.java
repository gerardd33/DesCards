package com.descards.flashcards.model.nonentity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RepetitionIntervalUpdateRequest {

	private Long cardId;

	private FlashcardStrength cardStrength;
}
