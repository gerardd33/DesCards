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
		System.out.println(requestDto);
		return HttpGuard.getResponse(() -> deckFacade.getCardPortion(deckId, requestDto));
	}

	@PostMapping("/{deckId}/update-cards")
	ResponseEntity<?> updateCards(@PathVariable long deckId, @RequestBody Set<FlashcardDto> cardsToUpdate) {
		return ResponseEntity.ok().build();
	}

	@PostMapping("/{deckId}/remove-card/{cardIdToRemove}")
	ResponseEntity<?> removeCard(@PathVariable long deckId, @PathVariable() long cardIdToRemove) {
		return ResponseEntity.ok().build();
	}

	@PostMapping("/{deckId}/remove-cards")
	ResponseEntity<?> removeCards(@PathVariable long deckId, @RequestBody Set<Long> cardIdsToRemove) {
		return ResponseEntity.ok().build();
	}

	@PostMapping("/{deckId}/add-card")
	ResponseEntity<?> addCard(@PathVariable long deckId, @RequestBody FlashcardDto cardToAdd) {
		return ResponseEntity.ok().build();
	}

	@PostMapping("/{deckId}/add-cards")
	ResponseEntity<?> addCard(@PathVariable long deckId, @RequestBody Set<FlashcardDto> cardsToAdd) {
		return ResponseEntity.ok().build();
	}
}
