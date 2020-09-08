package com.descards.flashcards.model.nonpersistent;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SortingField {

	INTERVAL("repetitionInterval"),
	CREATED("createdDate"),
	FRONT("front"),
	BACK("back");

	private final String attributeName;
}
