package com.descards.flashcards.api.controller;

import com.descards.flashcards.api.dto.DeckDto;
import com.descards.flashcards.service.facade.UserDecksFacade;
import com.descards.flashcards.util.controller.HttpGuard;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user-decks")
public class UserDecksController {

	private final UserDecksFacade userDecksFacade;

	@GetMapping("/{userId}")
	ResponseEntity<?> getDeckList(@PathVariable long userId) {
		return HttpGuard.getResponse(() -> userDecksFacade.getDeckList(userId));
	}

	@PostMapping("/{userId}")
	ResponseEntity<?> createDeck(@PathVariable long userId, @RequestBody DeckDto deckToCreateDto) {
		return HttpGuard.getResponse(() -> userDecksFacade.createDeck(userId, deckToCreateDto));
	}

	@PostMapping("/{userId}/remove-deck/{deckToRemoveId}")
	ResponseEntity<?> removeDeck(@PathVariable long userId, @PathVariable long deckToRemoveId) {
		return HttpGuard.getResponse(() -> userDecksFacade.removeDeck(userId, deckToRemoveId));
	}
}
