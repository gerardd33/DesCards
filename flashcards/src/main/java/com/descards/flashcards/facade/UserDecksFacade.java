package com.descards.flashcards.facade;

import com.descards.flashcards.model.Deck;

import java.util.List;

public interface UserDecksFacade {

	List<Deck> getDeckList(long userId);
}
