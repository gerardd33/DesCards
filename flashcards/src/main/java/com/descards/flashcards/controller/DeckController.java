package com.descards.flashcards.controller;

import com.descards.flashcards.facade.DeckFacade;
import com.descards.flashcards.model.Flashcard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/deck")
public class DeckController {

	@Autowired
	DeckFacade deckFacade;

	@GetMapping("/{deckId}")
	List<Flashcard> getFlashcardPortion(@PathVariable long deckId) {
		return deckFacade.getFlashcardPortion(deckId);
	}
}
