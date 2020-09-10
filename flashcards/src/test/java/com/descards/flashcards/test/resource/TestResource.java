package com.descards.flashcards.test.resource;

import com.descards.flashcards.model.entity.ApplicationUser;
import com.descards.flashcards.model.entity.Category;
import com.descards.flashcards.model.entity.Deck;
import com.descards.flashcards.model.entity.Flashcard;
import com.descards.flashcards.model.nonentity.FlashcardFactory;
import lombok.Getter;
import lombok.Setter;
import org.junit.rules.ExternalResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@Component
public class TestResource extends ExternalResource {

	private Deck deck;

	private Category category;

	private Flashcard flashcard;

	private ApplicationUser user;

	private FlashcardFactory flashcardFactory;

	@Autowired
	public TestResource(FlashcardFactory flashcardFactory) {
		this.flashcardFactory = flashcardFactory;

		user = new ApplicationUser();
		user.setId(1L);

		deck = new Deck("History deck", user);
		deck.setId(1L);

		category = new Category("Historical event");
		category.setId(1L);
		category.setSpecialFields(Stream.of("Date", "Who").collect(Collectors.toList()));

		Set<Flashcard> flashcards = Stream.of(
				flashcardFactory.getObject("Muslim invasion of Spain", "711"),
				flashcardFactory.getObject("Building of the Suez Canal", "1859"),
				flashcardFactory.getObject("American Civil War", "1861-1865")
		).collect(Collectors.toSet());

		AtomicLong index = new AtomicLong();
		flashcards.forEach(card -> {
			card.setDeck(deck);
			card.setId(index.incrementAndGet());
		});
		deck.getCards().addAll(flashcards);

		flashcard = deck.getCards().stream().findAny().orElseThrow(NoSuchElementException::new);
	}
}
