package com.descards.flashcards.model.nonentity;

import lombok.Data;

import java.time.Duration;

@Data
public class RepetitionInterval {

	private SchedulingAlgorithm algorithm;

	private Duration current;

	public RepetitionInterval(SchedulingAlgorithm algorithm) {
		this.algorithm = algorithm;
		this.current = algorithm.getInitial();
	}

	public Duration getAgain() {
		return algorithm.getAgainFor(this.current);
	}

	public Duration getHard() {
		return algorithm.getHardFor(this.current);
	}

	public Duration getOk() {
		return algorithm.getOkFor(this.current);
	}

	public Duration getEasy() {
		return algorithm.getEasyFor(this.current);
	}

	public void setAs(FlashcardStrength strength) {
		switch (strength) {
			case AGAIN:
				this.current = getAgain();
				break;
			case HARD:
				this.current = getHard();
				break;
			case OK:
				this.current = getOk();
				break;
			case EASY:
				this.current = getEasy();
				break;
		}
	}
}
