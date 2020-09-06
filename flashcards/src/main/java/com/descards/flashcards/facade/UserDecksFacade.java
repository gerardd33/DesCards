package com.descards.flashcards.facade;

import com.descards.flashcards.dto.DeckDto;

import java.util.List;

public interface UserDecksFacade {

	List<DeckDto> getDeckList(long userId);
}
