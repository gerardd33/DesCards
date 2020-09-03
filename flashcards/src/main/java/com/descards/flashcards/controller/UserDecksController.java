package com.descards.flashcards.controller;

import com.descards.flashcards.facade.UserDecksFacade;
import com.descards.flashcards.model.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-decks")
public class UserDecksController {

	@Autowired
	UserDecksFacade userDecksFacade;

	@GetMapping("/{userId}")
	List<Deck> getDeckList(@PathVariable long userId) {
		return userDecksFacade.getDeckList(userId);
	}

	@PostMapping("/{userId}")
	ResponseEntity<?> createDeck(@PathVariable long userId, @RequestBody Deck newDeck) {
		return ResponseEntity.ok().build();
	}

	@PostMapping("/{userId}/remove-deck/{deckId}")
	ResponseEntity<?> removeDeck(@PathVariable long userId, @PathVariable long deckIdToRemove) {
		return ResponseEntity.ok().build();
	}
}
