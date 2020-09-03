package com.descards.flashcards.service;

import com.descards.flashcards.facade.UserDecksFacade;
import com.descards.flashcards.model.Deck;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class UserDecksFacadeImpl implements UserDecksFacade {

	Deck dummyDeck;

	@Override
	public List<Deck> getDeckList(long userId) {
		return Collections.singletonList(dummyDeck);
	}
}
