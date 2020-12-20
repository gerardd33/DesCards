package generator.util.service;

import generator.model.Flashcard;
import generator.model.GeneratorRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
public class GeneratorRequestManager {

	private static final String EN_DASH_SPACE = " \u2013 ";

	private InformationFinder informationFinder;

	private FlashcardCreationRequestDispatcher flashcardCreationRequestDispatcher;

	public synchronized void processRequest(GeneratorRequest generatorRequest) {
		log.info("Processing: " + generatorRequest);

		generatorRequest.getSpecialFields().forEach(fieldLabel -> {
			String cardFront = generatorRequest.getQuery() + EN_DASH_SPACE + fieldLabel;
			String cardBack = informationFinder.findInformation(
					generatorRequest.getQuery(), fieldLabel);
			createFlashcard(generatorRequest.getDeckId(), cardFront, cardBack);
		});

		String cardFront = generatorRequest.getQuery();
		String cardBack = informationFinder.findInformation(generatorRequest.getQuery());
		createFlashcard(generatorRequest.getDeckId(), cardFront, cardBack);
	}

	private void createFlashcard(long deckId, String cardFront, String cardBack) {
		Flashcard flashcard = Flashcard.builder()
				.deckId(deckId)
				.front(cardFront)
				.back(cardBack)
				.build();

		log.info("Prepared flashcard creation request: " + flashcard);
		flashcardCreationRequestDispatcher.createFlashcard(flashcard);
	}
}
