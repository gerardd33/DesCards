package com.descards.flashcards.service.impl;

import com.descards.flashcards.api.dto.DeckInfoDto;
import com.descards.flashcards.api.dto.FlashcardDto;
import com.descards.flashcards.api.dto.FlashcardPortionRequestDto;
import com.descards.flashcards.api.dto.RepetitionIntervalUpdateRequestDto;
import com.descards.flashcards.model.entity.Deck;
import com.descards.flashcards.model.entity.Flashcard;
import com.descards.flashcards.model.nonentity.FlashcardPortionRequest;
import com.descards.flashcards.model.nonentity.RepetitionIntervalUpdateRequest;
import com.descards.flashcards.model.nonentity.SortingDirection;
import com.descards.flashcards.repository.DeckRepository;
import com.descards.flashcards.repository.FlashcardRepository;
import com.descards.flashcards.service.facade.DeckFacade;
import com.descards.flashcards.util.DeckInfoRetriever;
import com.descards.flashcards.util.mapper.DeckDtoMapper;
import com.descards.flashcards.util.mapper.FlashcardDtoMapper;
import com.descards.flashcards.util.mapper.FlashcardPortionRequestDtoMapper;
import com.descards.flashcards.util.mapper.RepetitionIntervalUpdateRequestDtoMapper;
import com.google.common.base.CaseFormat;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DeckFacadeImpl implements DeckFacade {

	DeckRepository deckRepository;

	FlashcardRepository flashcardRepository;

	DeckInfoRetriever deckInfoRetriever;

	@Override
	public List<FlashcardDto> getCardPortion(long deckId, FlashcardPortionRequestDto requestDto) {
		FlashcardPortionRequest request = FlashcardPortionRequestDtoMapper.convertFromDto(requestDto);

		Pageable criteria;
		if (request.getSortingDirection() == SortingDirection.DESCENDING) {
			criteria = PageRequest.of(request.getPageIndex(), request.getItemsPerPage(), Sort.by(
					CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, request.getSortBy().name()))
					.descending());
		} else {
			criteria = PageRequest.of(request.getPageIndex(), request.getItemsPerPage(), Sort.by(
					CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, request.getSortBy().name()))
					.ascending());
		}

		Collection<Flashcard> cardPortion = flashcardRepository.findAllByDeckId(deckId, criteria);
		return cardPortion.stream()
				.map(FlashcardDtoMapper::convertToDto)
				.collect(Collectors.toList());
	}

	@Override
	public DeckInfoDto getDeckInfo(long deckId) {
		Deck deck = deckRepository.findById(deckId)
				.orElseThrow(NoSuchElementException::new);

		deckInfoRetriever.setDeck(deck);
		Duration smallestInterval = deckInfoRetriever.getSmallestInterval();
		Duration greatestInterval = deckInfoRetriever.getGreatestInterval();
		LocalDateTime lastAddition = deckInfoRetriever.getLastAddition();

		return DeckInfoDto.builder()
				.deck(DeckDtoMapper.convertToDto(deck))
				.totalCards((long) deck.getCards().size())
				.smallestInterval(smallestInterval.toMinutes())
				.greatestInterval(greatestInterval.toMinutes())
				.lastAddition(lastAddition)
				.build();
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
				.map(FlashcardDtoMapper::convertFromDto)
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
	public void updateCard(long deckId, FlashcardDto cardToUpdateDto) {
		updateCards(deckId, Collections.singleton(cardToUpdateDto));
	}

	@Override
	public void updateCards(long deckId, Set<FlashcardDto> cardsToUpdateDtos) {
		if (!deckRepository.existsById(deckId)) {
			throw new NoSuchElementException();
		}

		for (FlashcardDto cardDto : cardsToUpdateDtos) {
			flashcardRepository.findById(cardDto.getId()).ifPresent(card -> {
				if (card.getDeck().getId() != deckId) {
					throw new IllegalArgumentException();
				}

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

	@Override
	public void updateInterval(long deckId, RepetitionIntervalUpdateRequestDto requestDto) {
		updateIntervals(deckId, Collections.singleton(requestDto));
	}

	@Override
	public void updateIntervals(long deckId, Set<RepetitionIntervalUpdateRequestDto> requestDtos) {
		if (!deckRepository.existsById(deckId)) {
			throw new NoSuchElementException();
		}

		for (RepetitionIntervalUpdateRequestDto requestDto : requestDtos) {
			RepetitionIntervalUpdateRequest request =
					RepetitionIntervalUpdateRequestDtoMapper.convertFromDto(requestDto);
			flashcardRepository.findById(request.getCardId()).ifPresent(card -> {
				if (card.getDeck().getId() != deckId) {
					throw new IllegalArgumentException();
				}

				card.getInterval().setFromStrength(request.getCardStrength());
				flashcardRepository.save(card);
			});
		}
	}
}
