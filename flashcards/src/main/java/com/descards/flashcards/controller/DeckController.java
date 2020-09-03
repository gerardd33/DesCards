package com.descards.flashcards.controller;

import com.descards.flashcards.facade.DeckFacade;
import com.descards.flashcards.model.Flashcard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/deck")
public class DeckController {

	@Autowired
	DeckFacade deckFacade;

	@GetMapping("/{deckId}")
	Set<Flashcard> getCardPortion(@PathVariable long deckId) {
		return deckFacade.getCardPortion(deckId);
	}

	@PostMapping("/{deckId}/update-cards")
	ResponseEntity<?> updateCards(@PathVariable long deckId, @RequestBody Set<Flashcard> cardsToUpdate) {
		return ResponseEntity.ok().build();
	}

	@PostMapping("/{deckId}/remove-card/{cardId}")
	ResponseEntity<?> removeCard(@PathVariable long deckId, @PathVariable long cardIdToRemove) {
		return ResponseEntity.ok().build();
	}

	@PostMapping("/{deckId}/remove-cards")
	ResponseEntity<?> removeCards(@PathVariable long deckId, @RequestBody Set<Long> cardIdsToRemove) {
		return ResponseEntity.ok().build();
	}

	@PostMapping("/{deckId}/add-card")
	ResponseEntity<?> addCard(@PathVariable long deckId, @RequestBody Flashcard cardToAdd) {
		return ResponseEntity.ok().build();
	}

	@PostMapping("/{deckId}/add-cards")
	ResponseEntity<?> addCard(@PathVariable long deckId, @RequestBody Set<Flashcard> cardsToAdd) {
		return ResponseEntity.ok().build();
	}
}
