package com.descards.flashcards.model.nonentity;

import lombok.NoArgsConstructor;

import java.time.Duration;

@NoArgsConstructor
public class BasicSpacedRepetitionAlgorithm implements SchedulingAlgorithm {

	private static final Duration LEARNING_INTERVAL = Duration.ofMinutes(5);

	private static final int LEARNING_REPETITIONS = 3;

	private static final Duration LEARNED = LEARNING_INTERVAL.multipliedBy(LEARNING_REPETITIONS);

	private static final Duration BASE_INTERVAL = Duration.ofDays(5);

	@Override
	public Duration getInitial() {
		return Duration.ZERO;
	}

	@Override
	public Duration getAgainFor(Duration duration) {
		return Duration.ZERO;
	}

	@Override
	public Duration getHardFor(Duration duration) {
		if (duration.compareTo(LEARNED) < 0) {
			return duration.plus(LEARNING_INTERVAL);
		} else {
			return BASE_INTERVAL;
		}
	}

	@Override
	public Duration getOkFor(Duration duration) {
		if (duration.compareTo(LEARNED) < 0) {
			return duration.plus(LEARNING_INTERVAL);
		} else if (duration.compareTo(BASE_INTERVAL) < 0) {
			return BASE_INTERVAL;
		} else {
			return duration.multipliedBy(2);
		}
	}

	@Override
	public Duration getEasyFor(Duration duration) {
		if (duration.compareTo(BASE_INTERVAL) < 0) {
			return BASE_INTERVAL;
		} else {
			return duration.multipliedBy(4);
		}
	}
}
