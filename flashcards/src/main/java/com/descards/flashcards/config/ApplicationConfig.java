package com.descards.flashcards.config;

import com.descards.flashcards.model.nonentity.RepetitionInterval;
import com.descards.flashcards.model.nonentity.SchedulingAlgorithm;
import com.descards.flashcards.model.nonentity.SpacedRepetitionAlgorithm;
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
}
