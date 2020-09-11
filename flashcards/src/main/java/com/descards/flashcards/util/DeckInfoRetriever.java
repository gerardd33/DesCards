package com.descards.flashcards.util;

import com.descards.flashcards.model.entity.Deck;
import com.descards.flashcards.model.entity.Flashcard;
import com.descards.flashcards.model.nonentity.RepetitionInterval;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.BinaryOperator;

@Getter
@Setter
@NoArgsConstructor
public class DeckInfoRetriever {

	private Deck deck;

	private static RepetitionInterval BASE_INTERVAL;

	@Autowired
	@Qualifier("baseRepetitionInterval")
	public void setBaseInterval(RepetitionInterval baseInterval) {
		BASE_INTERVAL = baseInterval;
	}

	public RepetitionInterval getSmallestInterval() {
		Optional<Flashcard> cardWithSmallestInterval = findCardWithLargest(
				(card1, card2) -> {
					Duration interval1 = card1.getInterval().getCurrent();
					Duration interval2 = card2.getInterval().getCurrent();
					return interval1.compareTo(interval2) <= 0 ? card1 : card2;
				});

		return cardWithSmallestInterval.isPresent() ?
				cardWithSmallestInterval.get().getInterval() : BASE_INTERVAL;
	}

	public RepetitionInterval getGreatestInterval() {
		Optional<Flashcard> cardWithGreatestInterval = findCardWithLargest(
				(card1, card2) -> {
					Duration interval1 = card1.getInterval().getCurrent();
					Duration interval2 = card2.getInterval().getCurrent();
					return interval1.compareTo(interval2) > 0 ? card1 : card2;
				});

		return cardWithGreatestInterval.isPresent() ?
				cardWithGreatestInterval.get().getInterval() : BASE_INTERVAL;
	}

	public LocalDateTime getLastAddition() {
		Optional<Flashcard> cardLastAdded = findCardWithLargest(
				(card1, card2) -> {
					LocalDateTime time1 = card1.getCreatedTime();
					LocalDateTime time2 = card2.getCreatedTime();
					return time1.compareTo(time2) > 0 ? card1 : card2;
				});

		return cardLastAdded.isPresent() ?
				cardLastAdded.get().getCreatedTime() : LocalDateTime.now();
	}

	private Optional<Flashcard> findCardWithLargest(BinaryOperator<Flashcard> reduceBy) {
		return this.deck.getCards().stream().reduce(reduceBy);
	}
}
