package com.descards.flashcards.service;

import com.descards.flashcards.dto.FlashcardDto;
import com.descards.flashcards.dto.FlashcardPortionRequestDto;
import com.descards.flashcards.dto.converter.FlashcardDtoConverter;
import com.descards.flashcards.dto.converter.FlashcardPortionRequestDtoConverter;
import com.descards.flashcards.facade.DeckFacade;
import com.descards.flashcards.model.entity.Deck;
import com.descards.flashcards.model.entity.Flashcard;
import com.descards.flashcards.model.nonpersistent.FlashcardPortionRequest;
import com.descards.flashcards.model.nonpersistent.SortingDirection;
import com.descards.flashcards.repository.DeckRepository;
import com.descards.flashcards.repository.FlashcardRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DeckFacadeImpl implements DeckFacade {

	DeckRepository deckRepository;

	FlashcardRepository flashcardRepository;

	@Override
	public List<FlashcardDto> getCardPortion(long deckId, FlashcardPortionRequestDto requestDto) {
		FlashcardPortionRequest request = FlashcardPortionRequestDtoConverter.convertFromDto(requestDto);

		Pageable criteria;
		if (request.getSortingDirection() == SortingDirection.DESCENDING) {
			criteria = PageRequest.of(request.getOffset(), request.getLimit(),
					Sort.by(request.getSortBy().getAttributeName()).descending());
		} else {
			criteria = PageRequest.of(request.getOffset(), request.getLimit(),
					Sort.by(request.getSortBy().getAttributeName()).ascending());
		}

		Collection<Flashcard> cardPortion = flashcardRepository.findAllByDeckId(deckId, criteria);
		return cardPortion.stream()
				.map(FlashcardDtoConverter::convertToDto)
				.collect(Collectors.toList());
	}

	@Override
	public void addCard(long deckId, FlashcardDto cardToAddDto) {
		addCards(deckId, Collections.singleton(cardToAddDto));
	}

	@Override
	public void addCards(long deckId, Set<FlashcardDto> cardsToAddDtos) {
		Deck deck = deckRepository.findById(deckId)
				.orElseThrow(NoSuchElementException::new);

		Set<Flashcard> cardsToAdd = cardsToAddDtos.stream()
				.map(FlashcardDtoConverter::convertFromDto)
				.collect(Collectors.toSet());

		cardsToAdd.forEach(card -> card.setDeck(deck));
		deck.getCards().addAll(cardsToAdd);

		flashcardRepository.saveAll(cardsToAdd);
		deckRepository.save(deck);
	}

	@Override
	public void removeCard(long deckId, long cardToRemoveId) {
		removeCards(deckId, Collections.singleton(cardToRemoveId));
	}

	@Override
	public void removeCards(long deckId, Set<Long> cardsToRemoveIds) {
		if (!deckRepository.existsById(deckId)) {
			throw new NoSuchElementException();
		}

		Set<Flashcard> cardsToRemove = flashcardRepository
				.findAllById(cardsToRemoveIds).stream()
				.filter(card -> card.getDeck().getId() == deckId)
				.collect(Collectors.toSet());

		flashcardRepository.deleteAll(cardsToRemove);
	}

	@Override
	public void updateCards(long deckId, Set<FlashcardDto> cardsToUpdateDtos) {
		if (!deckRepository.existsById(deckId)) {
			throw new NoSuchElementException();
		}

		cardsToUpdateDtos.forEach(cardDto -> {
			Flashcard card = flashcardRepository.findById(cardDto.getId())
					.orElseThrow(NoSuchElementException::new);

			card.setFront(cardDto.getFront());
			card.setBack(cardDto.getBack());

			if (cardDto.getDeckId() != null) {
				card.setDeck(deckRepository.findById(cardDto.getDeckId())
						.orElseThrow(NoSuchElementException::new));
			}

			flashcardRepository.save(card);
		});
	}
}
