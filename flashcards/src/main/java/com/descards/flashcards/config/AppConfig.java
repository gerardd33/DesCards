package com.descards.flashcards.config;

import com.descards.flashcards.facade.DeckFacade;
import com.descards.flashcards.repository.DeckRepository;
import com.descards.flashcards.service.DeckFacadeImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {
	"com.descards.flashcards.repository"
})
public class AppConfig {
	@Bean
	public DeckFacade deckFacade(DeckRepository deckRepository) {
		return new DeckFacadeImpl(deckRepository);
	}
}
