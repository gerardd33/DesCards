package com.descards.flashcards.clirunner;

import com.descards.flashcards.model.Deck;
import com.descards.flashcards.model.Flashcard;
import com.descards.flashcards.model.User;
import com.descards.flashcards.repository.DeckRepository;
import com.descards.flashcards.repository.FlashcardRepository;
import com.descards.flashcards.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class CliRunner implements CommandLineRunner {

	private final UserRepository userRepository;

	private final FlashcardRepository flashcardRepository;

	private final DeckRepository deckRepository;

	@Override
	public void run(String... args) {
		persistSampleData();
	}

	private void persistSampleData() {

		User gerard = new User("Gerard");
		Deck historyDeck = new Deck("History", gerard);

		Flashcard fact1 = new Flashcard(historyDeck, "battle of waterloo", "1815", Duration.ofDays(2));
		Flashcard fact2 = new Flashcard(historyDeck, "xx", "2", Duration.ofDays(3));
		Flashcard fact3 = new Flashcard(historyDeck, "yy", "3", Duration.ofDays(4));
		Flashcard fact4 = new Flashcard(historyDeck, "zz", "4", Duration.ofDays(5));

		Set<Flashcard> facts = new HashSet<>();
		facts.add(fact1);
		facts.add(fact2);
		facts.add(fact3);
		facts.add(fact4);

		historyDeck.getCards().addAll(facts);
		gerard.getDecks().add(historyDeck);

		userRepository.save(gerard);
		deckRepository.save(historyDeck);
		flashcardRepository.saveAll(facts);

		System.out.println("Number of flashcards: " + flashcardRepository.count());
		System.out.println(historyDeck.getId());
	}
}
