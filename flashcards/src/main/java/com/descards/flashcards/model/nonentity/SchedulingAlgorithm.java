package com.descards.flashcards.model.nonentity;

import java.time.Duration;

public interface SchedulingAlgorithm {

	Duration getInitial();

	Duration getAgainFor(Duration duration);

	Duration getHardFor(Duration duration);

	Duration getOkFor(Duration duration);

	Duration getEasyFor(Duration duration);
}
