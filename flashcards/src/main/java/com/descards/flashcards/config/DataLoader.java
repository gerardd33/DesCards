package com.descards.flashcards.config;

import com.descards.flashcards.model.entity.ApplicationUser;
import com.descards.flashcards.model.entity.Category;
import com.descards.flashcards.model.entity.Deck;
import com.descards.flashcards.model.entity.Flashcard;
import com.descards.flashcards.model.nonentity.FlashcardFactory;
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

	private final FlashcardFactory flashcardFactory;

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

		Category historicalEvent = new Category("Historical event");
		historicalEvent.getSpecialFields().addAll(Arrays.asList(
				"Known for", "When", "Where"
		));

		Category historicalPerson = new Category("Historical person");
		historicalPerson.getSpecialFields().addAll(Arrays.asList(
				"Known for", "Born/died", "Reign dates", "Place of birth", "Nationality"
		));

		Category battle = new Category("Battle");
		battle.getSpecialFields().addAll(Arrays.asList(
				"Known for", "When", "Who fought", "Result"
		));

		Category place = new Category("Place");
		place.getSpecialFields().addAll(Arrays.asList(
				"Known for", "Where", "Which country"
		));

		Category bookOrWorkOfArt = new Category("Book or work of art");
		bookOrWorkOfArt.getSpecialFields().addAll(Arrays.asList(
				"Known for", "Created date", "Author", "Original title"
		));

		Category language = new Category("Language");
		language.getSpecialFields().addAll(Arrays.asList(
				"Number of native speakers", "Countries spoken", "Language family"
		));

		Category livingPerson = new Category("Living person");
		livingPerson.getSpecialFields().addAll(Arrays.asList(
				"Known for", "Date of birth", "Age", "Nationality", "Place of birth"
		));

		Collection<Category> categories = Stream.of(
				historicalEvent, historicalPerson, battle, place,
				bookOrWorkOfArt, language, livingPerson
		).collect(Collectors.toList());
		categoryRepository.saveAll(categories);
	}

	private void loadDummyData() {
		Flashcard card1 = flashcardFactory.getObject("Death of Socrates", "399 BC");
		Flashcard card2 = flashcardFactory.getObject("Muslim invasion of Spain", "711");
		Flashcard card3 = flashcardFactory.getObject("Building of the Suez Canal", "1859");
		Flashcard card4 = flashcardFactory.getObject("American Civil War", "1861-1865");
		Flashcard card5 = flashcardFactory.getObject("Julius Caesar Death", "44 BC");
		Flashcard card6 = flashcardFactory.getObject("Irish War of Independence", "1919-1921");

		ApplicationUser user = new ApplicationUser();
		Deck historyDeck = new Deck("History deck", user);

		Collection<Flashcard> flashcards = Stream.of(card1, card2).collect(Collectors.toSet());
		historyDeck.getCards().addAll(flashcards);
		historyDeck.getCards().forEach(card -> card.setDeck(historyDeck));
		historyDeck.setUser(user);
		user.getDecks().add(historyDeck);

		applicationUserRepository.save(user);
		deckRepository.save(historyDeck);
		flashcardRepository.saveAll(flashcards);
	}
}
