package com.descards.flashcards.config;

import com.descards.flashcards.model.nonentity.BasicSpacedRepetitionAlgorithm;
import com.descards.flashcards.model.nonentity.FlashcardFactory;
import com.descards.flashcards.model.nonentity.RepetitionInterval;
import com.descards.flashcards.model.nonentity.SchedulingAlgorithm;
import com.descards.flashcards.util.DeckInfoRetriever;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ApplicationConfig {

	@Bean
	public SchedulingAlgorithm schedulingAlgorithm() {
		return new BasicSpacedRepetitionAlgorithm();
	}

	@Bean
	@Primary
	public RepetitionInterval repetitionInterval() {
		return new RepetitionInterval(schedulingAlgorithm());
	}

	@Bean
	public RepetitionInterval baseRepetitionInterval() {
		return new RepetitionInterval(schedulingAlgorithm());
	}

	@Bean
	public FlashcardFactory flashcardFactory() {
		return new FlashcardFactory(repetitionInterval());
	}

	@Bean
	public DeckInfoRetriever deckInfoRetriever() {
		return new DeckInfoRetriever();
	}
}
