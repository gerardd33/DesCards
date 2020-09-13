package generator.util.service;

import generator.model.Flashcard;
import generator.model.GeneratorRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
public class GeneratorRequestManager {

	private GeneratorRequest generatorRequest;

	private InformationFinder informationFinder;

	private FlashcardCreationRequestDispatcher flashcardCreationRequestDispatcher;

	public void processRequest() {
		log.info("Processing: " + generatorRequest);
		String cardFront = generatorRequest.getQuery() + " "
				+ String.join(" ", generatorRequest.getSpecialFields());

		String cardBack = informationFinder.findInformation(generatorRequest);

		Flashcard flashcard =
				Flashcard.builder()
					.deckId(generatorRequest.getDeckId())
					.front(cardFront)
					.back(cardBack)
					.build();
		log.info("Prepared flashcard creation request: " + flashcard);

		// TODO get feedback and release the semaphore
		flashcardCreationRequestDispatcher.createFlashcard(flashcard);
	}
}
