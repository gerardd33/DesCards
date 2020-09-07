package com.descards.flashcards.config;

import com.descards.flashcards.model.ApplicationUser;
import com.descards.flashcards.model.Category;
import com.descards.flashcards.model.Deck;
import com.descards.flashcards.model.Flashcard;
import com.descards.flashcards.repository.ApplicationUserRepository;
import com.descards.flashcards.repository.CategoryRepository;
import com.descards.flashcards.repository.DeckRepository;
import com.descards.flashcards.repository.FlashcardRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@AllArgsConstructor
public class DataLoader implements ApplicationRunner {

	private final ApplicationUserRepository applicationUserRepository;

	private final DeckRepository deckRepository;

	private final FlashcardRepository flashcardRepository;

	private final CategoryRepository categoryRepository;

	@Override
	public void run(ApplicationArguments args) {
//		clearDatabase();
//		initialiseDatabase();
	}

	private void clearDatabase() {
		categoryRepository.deleteAll();
		flashcardRepository.deleteAll();
		deckRepository.deleteAll();
		applicationUserRepository.deleteAll();
	}

	private void initialiseDatabase() {
		loadCategories();
		loadDummyData();
	}

	private void loadCategories() {
		Category category1 = new Category("Historical event");
		Category category2 = new Category("Person");

		category1.getSpecialFields().addAll(Arrays.asList("Date", "Who", "Where"));
		category2.getSpecialFields().addAll(Arrays.asList("Nationality", "Date of death"));

		categoryRepository.save(category1);
		categoryRepository.save(category2);
	}

	private void loadDummyData() {
		Flashcard card1 = new Flashcard(null, "Death of Socrates", "399 BC", Duration.ofDays(8));
		Flashcard card2 = new Flashcard(null, "Muslim invasion of Spain", "711", Duration.ofDays(2));
		Flashcard card3 = new Flashcard(null, "Building of the Suez Canal", "1859", Duration.ofDays(3));
		Flashcard card4 = new Flashcard(null, "American Civil War", "1861-1865", Duration.ofDays(10));

		ApplicationUser user = new ApplicationUser("Moshe Engelbaum");
		Deck historyDeck = new Deck("History deck", user);

		Collection<Flashcard> flashcards = Stream.of(card1, card2, card3, card4).collect(Collectors.toSet());
		historyDeck.getCards().addAll(flashcards);
		historyDeck.getCards().forEach(card -> card.setDeck(historyDeck));
		historyDeck.setUser(user);
		user.getDecks().add(historyDeck);

		applicationUserRepository.save(user);
		deckRepository.save(historyDeck);
		flashcardRepository.saveAll(flashcards);
	}
}
