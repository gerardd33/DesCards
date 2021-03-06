package com.descards.flashcards.api.controller;

import com.descards.flashcards.api.dto.FlashcardDto;
import com.descards.flashcards.api.dto.FlashcardPortionRequestDto;
import com.descards.flashcards.api.dto.RepetitionIntervalUpdateRequestDto;
import com.descards.flashcards.service.facade.DeckFacade;
import com.descards.flashcards.util.controller.HttpGuard;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/deck")
public class DeckController {

	private final DeckFacade deckFacade;

	@GetMapping("/{deckId}")
	ResponseEntity<?> getCardPortion(@PathVariable long deckId, @RequestBody FlashcardPortionRequestDto requestDto) {
		return HttpGuard.getResponse(() -> deckFacade.getCardPortion(deckId, requestDto));
	}

	@GetMapping("/{deckId}/info")
	ResponseEntity<?> getDeckInfo(@PathVariable long deckId) {
		return HttpGuard.getResponse(() -> deckFacade.getDeckInfo(deckId));
	}

	@PostMapping("/{deckId}/add-card")
	ResponseEntity<?> addCard(@PathVariable long deckId, @RequestBody FlashcardDto cardToAddDto) {
		return HttpGuard.getResponse(() -> deckFacade.addCard(deckId, cardToAddDto));
	}

	@PostMapping("/{deckId}/add-cards")
	ResponseEntity<?> addCards(@PathVariable long deckId, @RequestBody Set<FlashcardDto> cardsToAddDtos) {
		return HttpGuard.getResponse(() -> deckFacade.addCards(deckId, cardsToAddDtos));
	}

	@PostMapping("/{deckId}/remove-card/{cardToRemoveId}")
	ResponseEntity<?> removeCard(@PathVariable long deckId, @PathVariable() long cardToRemoveId) {
		return HttpGuard.getResponse(() -> deckFacade.removeCard(deckId, cardToRemoveId));
	}

	@PostMapping("/{deckId}/remove-cards")
	ResponseEntity<?> removeCards(@PathVariable long deckId, @RequestBody Set<Long> cardsToRemoveIds) {
		return HttpGuard.getResponse(() -> deckFacade.removeCards(deckId, cardsToRemoveIds));
	}

	@PostMapping("/{deckId}/update-card")
	ResponseEntity<?> updateCards(@PathVariable long deckId, @RequestBody FlashcardDto cardToUpdateDto) {
		return HttpGuard.getResponse(() -> deckFacade.updateCard(deckId, cardToUpdateDto));
	}

	@PostMapping("/{deckId}/update-cards")
	ResponseEntity<?> updateCards(@PathVariable long deckId, @RequestBody Set<FlashcardDto> cardsToUpdateDtos) {
		return HttpGuard.getResponse(() -> deckFacade.updateCards(deckId, cardsToUpdateDtos));
	}

	@PostMapping("/{deckId}/update-interval")
	ResponseEntity<?> updateIntervals(@PathVariable long deckId, @RequestBody RepetitionIntervalUpdateRequestDto requestDto) {
		return HttpGuard.getResponse(() -> deckFacade.updateInterval(deckId, requestDto));
	}

	@PostMapping("/{deckId}/update-intervals")
	ResponseEntity<?> updateIntervals(@PathVariable long deckId, @RequestBody Set<RepetitionIntervalUpdateRequestDto> requestDtos) {
		return HttpGuard.getResponse(() -> deckFacade.updateIntervals(deckId, requestDtos));
	}
}
