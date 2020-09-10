package com.descards.flashcards.model.nonentity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum SortingDirection {

	ASCENDING("asc"),
	DESCENDING("desc");

	private final String apiName;

	public static SortingDirection parseApiName(String apiName) {
		return Arrays.stream(SortingDirection.values())
				.filter(value -> value.getApiName().equals(apiName))
				.findAny()
				.orElseThrow(IllegalArgumentException::new);
	}
}