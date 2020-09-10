package com.descards.flashcards.model.nonentity;

import com.descards.flashcards.model.entity.Flashcard;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class FlashcardFactory {

	private RepetitionInterval repetitionInterval;

	public Flashcard getObject() {
		return new Flashcard(repetitionInterval);
	}

	public Flashcard getObject(String front, String back) {
		return new Flashcard(repetitionInterval, front, back);
	}
}
