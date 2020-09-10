package com.descards.flashcards.config;

import com.descards.flashcards.model.nonentity.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

	@Bean
	public SchedulingAlgorithm getSchedulingAlgorithm() {
		return new SpacedRepetitionAlgorithm();
	}

	@Bean
	public RepetitionInterval getRepetitionInterval() {
		return new RepetitionInterval(getSchedulingAlgorithm());
	}

	@Bean
	public FlashcardFactory getFlashcardFactory() {
		return new FlashcardFactory(getRepetitionInterval());
	}
}
