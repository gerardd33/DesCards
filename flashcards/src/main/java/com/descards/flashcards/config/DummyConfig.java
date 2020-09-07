package com.descards.flashcards.config;

import com.descards.flashcards.facade.DeckFacade;
import com.descards.flashcards.model.Category;
import com.descards.flashcards.model.Deck;
import com.descards.flashcards.model.Flashcard;
import com.descards.flashcards.model.ApplicationUser;
import com.descards.flashcards.service.DeckFacadeImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.Duration;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@EnableJpaRepositories(basePackages = {
	"com.descards.flashcards.repository"
})
public class DummyConfig {
	@Bean
	public ApplicationUser dummyUser() {
		return new ApplicationUser("Tomasz Kowalski");
	}

	@Bean
	public Set<Flashcard> dummyFlashcardCollection() {
		return Stream.of(
			new Flashcard(null, "Death of Socrates", "399 BC", Duration.ofDays(8)),
			new Flashcard(null, "Muslim invasion of Spain", "711", Duration.ofDays(2)),
			new Flashcard(null, "Building of the Suez Canal", "1859", Duration.ofDays(3)),
			new Flashcard(null, "American Civil War", "1861-1865", Duration.ofDays(10))
		).collect(Collectors.toSet());
	}

	@Bean
	public Deck dummyDeck() {
		ApplicationUser user = dummyUser();
		Set<Flashcard> flashcardCollection = dummyFlashcardCollection();

		Deck dummyDeck = new Deck("History deck", user);
		flashcardCollection.forEach(card -> card.setDeck(dummyDeck));
		dummyDeck.getCards().addAll(flashcardCollection);
		dummyDeck.setUser(user);

		return dummyDeck;
	}

	@Bean
	public Category dummyCategory() {
		Category dummyCategory = new Category("Historical event");
		dummyCategory.getSpecialFields().addAll(Arrays.asList("Date", "Who", "Where"));
		return dummyCategory;
	}

	@Bean
	public DeckFacade deckFacade() {
		return new DeckFacadeImpl(dummyDeck());
	}
}