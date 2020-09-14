package generator.util.service;

import generator.api.dto.FlashcardDto;
import generator.model.Flashcard;
import generator.util.api.mapper.FlashcardDtoMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@AllArgsConstructor
public class FlashcardCreationRequestDispatcher {

	WebClient flashcardsApi;

	// TODO some error handling, feedback etc
	public void createFlashcard(Flashcard flashcard) {
		long deckId = flashcard.getDeckId();
		log.info("Creating flashcard in deck " + deckId);

		FlashcardDto flashcardDto = FlashcardDtoMapper.mapToDto(flashcard);
		flashcardsApi.post()
				.uri("/deck/" + deckId + "/add-card")
				.body(BodyInserters.fromValue(flashcardDto))
				.exchange()
				.block();

		log.info("Creation completed");
	}
}
