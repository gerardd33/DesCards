package com.descards.flashcards.model.nonentity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum FlashcardStrength {

	AGAIN("again"),
	HARD("hard"),
	OK("ok"),
	EASY("easy");

	private final String apiName;

	public static FlashcardStrength parseApiName(String apiName) {
		return Arrays.stream(FlashcardStrength.values())
				.filter(value -> value.getApiName().equals(apiName))
				.findAny()
				.orElseThrow(IllegalArgumentException::new);
	}
}
