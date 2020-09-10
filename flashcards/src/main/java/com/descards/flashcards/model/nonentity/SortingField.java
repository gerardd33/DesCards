package com.descards.flashcards.model.nonentity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SortingField {

	INTERVAL("interval"),
	CREATED("createdTime"),
	FRONT("front"),
	BACK("back");

	private final String attributeName;
}
