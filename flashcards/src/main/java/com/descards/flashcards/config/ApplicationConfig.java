package com.descards.flashcards.config;

import com.descards.flashcards.model.nonentity.BasicSpacedRepetitionAlgorithm;
import com.descards.flashcards.model.nonentity.FlashcardFactory;
import com.descards.flashcards.model.nonentity.IntervalFactory;
import com.descards.flashcards.model.nonentity.RepetitionInterval;
import com.descards.flashcards.model.nonentity.SchedulingAlgorithm;
import com.descards.flashcards.util.service.DeckInfoRetriever;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ApplicationConfig {

  @Bean
  public SchedulingAlgorithm schedulingAlgorithm() {
    return new BasicSpacedRepetitionAlgorithm();
  }

  @Bean
  public IntervalFactory intervalFactory() {
    return new IntervalFactory(schedulingAlgorithm());
  }

  @Bean
  public FlashcardFactory flashcardFactory() {
    return new FlashcardFactory(intervalFactory());
  }

  @Bean
  @Scope("prototype")
  public RepetitionInterval baseRepetitionInterval() {
    return intervalFactory().getObject();
  }

  @Bean
  public DeckInfoRetriever deckInfoRetriever() {
    return new DeckInfoRetriever();
  }
}
