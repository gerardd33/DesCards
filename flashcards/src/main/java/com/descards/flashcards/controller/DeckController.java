package com.descards.flashcards.controller;

import com.descards.flashcards.dto.FlashcardDto;
import com.descards.flashcards.dto.FlashcardPortionRequestDto;
import com.descards.flashcards.facade.DeckFacade;
import com.descards.flashcards.util.HttpGuard;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/deck")
public class DeckController {

	DeckFacade deckFacade;

	@GetMapping("/{deckId}")
	ResponseEntity<?> getCardPortion(@PathVariable long deckId, @RequestBody FlashcardPortionRequestDto requestDto) {
		return HttpGuard.getResponse(() -> deckFacade.getCardPortion(deckId, requestDto));
	}

	@PostMapping("/{deckId}/update-cards")
	ResponseEntity<?> updateCards(@PathVariable long deckId, @RequestBody Set<FlashcardDto> cardsToUpdateDtos) {
		return ResponseEntity.ok().build();
	}

	@PostMapping("/{deckId}/remove-card/{cardToRemoveId}")
	ResponseEntity<?> removeCard(@PathVariable long deckId, @PathVariable() long cardToRemoveId) {
		return ResponseEntity.ok().build();
	}

	@PostMapping("/{deckId}/remove-cards")
	ResponseEntity<?> removeCards(@PathVariable long deckId, @RequestBody Set<Long> cardsToRemoveIds) {
		return ResponseEntity.ok().build();
	}

	@PostMapping("/{deckId}/add-card")
	ResponseEntity<?> addCard(@PathVariable long deckId, @RequestBody FlashcardDto cardToAddDto) {
		return HttpGuard.getResponse(() -> deckFacade.addCard(deckId, cardToAddDto));
	}

	@PostMapping("/{deckId}/add-cards")
	ResponseEntity<?> addCards(@PathVariable long deckId, @RequestBody Set<FlashcardDto> cardsToAddDtos) {
		return HttpGuard.getResponse(() -> deckFacade.addCards(deckId, cardsToAddDtos));
	}
}
