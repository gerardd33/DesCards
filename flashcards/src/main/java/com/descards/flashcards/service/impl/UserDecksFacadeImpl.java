package com.descards.flashcards.service.impl;

import com.descards.flashcards.api.dto.DeckDto;
import com.descards.flashcards.model.entity.ApplicationUser;
import com.descards.flashcards.model.entity.Deck;
import com.descards.flashcards.repository.ApplicationUserRepository;
import com.descards.flashcards.repository.DeckRepository;
import com.descards.flashcards.repository.FlashcardRepository;
import com.descards.flashcards.service.facade.UserDecksFacade;
import com.descards.flashcards.util.api.mapper.DeckDtoMapper;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDecksFacadeImpl implements UserDecksFacade {

	ApplicationUserRepository applicationUserRepository;

	DeckRepository deckRepository;

	FlashcardRepository flashcardRepository;

	@Override
	public List<DeckDto> getDeckList(long userId) {
		ApplicationUser user = applicationUserRepository.findById(userId)
				.orElseGet(() -> applicationUserRepository.save(new ApplicationUser()));

		return user.getDecks().stream()
				.map(DeckDtoMapper::mapToDto)
				.collect(Collectors.toList());
	}

	@Override
	public void createDeck(long userId, DeckDto deckToCreateDto) {
		ApplicationUser user = applicationUserRepository.findById(userId)
				.orElseGet(() -> applicationUserRepository.save(new ApplicationUser()));
		Deck deckToAdd = DeckDtoMapper.mapFromDto(deckToCreateDto);

		deckToAdd.setUser(user);
		deckRepository.save(deckToAdd);
	}

	@Override
	public void removeDeck(long userId, long deckToRemoveId) {
		ApplicationUser user = applicationUserRepository.findById(userId)
				.orElseGet(() -> applicationUserRepository.save(new ApplicationUser()));
		Deck deck = deckRepository.findById(deckToRemoveId)
				.orElseThrow(NoSuchElementException::new);

		user.getDecks().remove(deck);

		applicationUserRepository.save(user);
		flashcardRepository.deleteAll(deck.getCards());
		deckRepository.delete(deck);
	}
}
