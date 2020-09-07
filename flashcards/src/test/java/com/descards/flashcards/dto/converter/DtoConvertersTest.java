package com.descards.flashcards.dto.converter;

import com.descards.flashcards.dto.CategoryDto;
import com.descards.flashcards.dto.DeckDto;
import com.descards.flashcards.dto.FlashcardDto;
import com.descards.flashcards.dto.UserDto;
import com.descards.flashcards.model.Category;
import com.descards.flashcards.model.Deck;
import com.descards.flashcards.model.Flashcard;
import com.descards.flashcards.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DtoConvertersTest {

	private Deck deck;

	private Category category;

	private Flashcard flashcard;

	private User user;

	@BeforeEach
	void setUp() {
		user = new User("Tomasz Kowalski");
		deck = new Deck("History deck", user);
		category = new Category("Historical event");
		category.setSpecialFields(Arrays.asList("Date", "Who"));

		Set<Flashcard> flashcards = Stream.of(new Flashcard(null, "Death of Socrates", "399 BC", Duration.ofDays(8)), new Flashcard(null, "Muslim invasion of Spain", "711", Duration.ofDays(2)), new Flashcard(null, "Building of the Suez Canal", "1859", Duration.ofDays(3)), new Flashcard(null, "American Civil War", "1861-1865", Duration.ofDays(10))).collect(Collectors.toSet());

		flashcards.forEach(card -> card.setDeck(deck));

		deck.setCards(flashcards);
		flashcard = deck.getCards().stream().findAny().orElseThrow(NoSuchElementException::new);
		user = deck.getUser();
	}

	@Test
	void shouldConvertUserEntityToDto() {
		UserDto userDto = UserDtoConverter.convertToDto(user);
		assertEquals(user.getId(), userDto.getId());
		assertEquals(user.getName(), userDto.getName());
	}

	@Test
	void shouldConvertFlashcardEntityToDto() {
		FlashcardDto flashcardDto = FlashcardDtoConverter.convertToDto(flashcard);
		assertEquals(flashcard.getId(), flashcardDto.getId());
		assertEquals(flashcard.getFront(), flashcardDto.getFront());
		assertEquals(flashcard.getBack(), flashcardDto.getBack());
		assertEquals(deck.getId(), flashcardDto.getDeckId());
		assertEquals(flashcard.getRepetitionInterval(), flashcardDto.getInterval());
	}

	@Test
	void shouldConvertDeckEntityToDto() {
		DeckDto deckDto = DeckDtoConverter.convertToDto(deck);
		assertEquals(deck.getId(), deckDto.getId());
		assertEquals(user.getId(), deckDto.getUserId());
		assertEquals(deck.getName(), deckDto.getName());
	}

	@Test
	void shouldConvertCategoryEntityToDto() {
		CategoryDto categoryDto = CategoryDtoConverter.convertToDto(category);
		assertEquals(category.getId(), categoryDto.getId());
		assertEquals(category.getName(), categoryDto.getName());
	}
}
