package com.descards.flashcards.test.resource;

import com.descards.flashcards.model.entity.ApplicationUser;
import com.descards.flashcards.model.entity.Category;
import com.descards.flashcards.model.entity.Deck;
import com.descards.flashcards.model.entity.Flashcard;
import lombok.Getter;
import lombok.Setter;
import org.junit.rules.ExternalResource;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
public class TestResource extends ExternalResource {

	private Deck deck;

	private Category category;

	private Flashcard flashcard;

	private ApplicationUser user;

	public TestResource() {
		user = new ApplicationUser();
		user.setId(1L);

		deck = new Deck("History deck", user);
		deck.setId(1L);

		category = new Category("Historical event");
		category.setId(1L);
		category.setSpecialFields(Stream.of("Date", "Who").collect(Collectors.toList()));

		Set<Flashcard> flashcards = Stream.of(
				new Flashcard("Muslim invasion of Spain", "711"),
				new Flashcard("Building of the Suez Canal", "1859"),
				new Flashcard("American Civil War", "1861-1865")
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
