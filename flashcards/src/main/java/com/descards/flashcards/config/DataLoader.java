package com.descards.flashcards.config;

import com.descards.flashcards.model.entity.ApplicationUser;
import com.descards.flashcards.model.entity.Category;
import com.descards.flashcards.model.entity.Deck;
import com.descards.flashcards.model.entity.Flashcard;
import com.descards.flashcards.repository.ApplicationUserRepository;
import com.descards.flashcards.repository.CategoryRepository;
import com.descards.flashcards.repository.DeckRepository;
import com.descards.flashcards.repository.FlashcardRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

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
		clearDatabase();
		initialiseDatabase();
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
		Flashcard card1 = new Flashcard("Death of Socrates", "399 BC");
		Flashcard card2 = new Flashcard("Muslim invasion of Spain", "711");
		Flashcard card3 = new Flashcard("Building of the Suez Canal", "1859");
		Flashcard card4 = new Flashcard("American Civil War", "1861-1865");
		Flashcard card5 = new Flashcard("Julius Caesar Death", "44 BC");
		Flashcard card6 = new Flashcard("Irish War of Independence", "1919-1921");

		ApplicationUser user = new ApplicationUser();
		Deck historyDeck = new Deck("History deck", user);

		Collection<Flashcard> flashcards = Stream.of(card1, card2, card3, card4, card5, card6).collect(Collectors.toSet());
		historyDeck.getCards().addAll(flashcards);
		historyDeck.getCards().forEach(card -> card.setDeck(historyDeck));
		historyDeck.setUser(user);
		user.getDecks().add(historyDeck);

		applicationUserRepository.save(user);
		deckRepository.save(historyDeck);
		flashcardRepository.saveAll(flashcards);
	}
}
