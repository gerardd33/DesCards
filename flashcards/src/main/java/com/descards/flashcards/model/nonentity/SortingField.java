package com.descards.flashcards.model.nonentity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum SortingField {

	INTERVAL("interval"),
	CREATED_TIME("created"),
	FRONT("front"),
	BACK("back");

	private final String apiName;

	public static SortingField parseApiName(String apiName) {
		return Arrays.stream(SortingField.values())
				.filter(value -> value.getApiName().equals(apiName))
				.findAny()
				.orElseThrow(IllegalArgumentException::new);
	}
}
