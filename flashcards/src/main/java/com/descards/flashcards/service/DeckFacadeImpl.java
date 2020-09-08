package com.descards.flashcards.service;

import com.descards.flashcards.dto.FlashcardDto;
import com.descards.flashcards.dto.converter.FlashcardDtoConverter;
import com.descards.flashcards.facade.DeckFacade;
import com.descards.flashcards.model.entity.Flashcard;
import com.descards.flashcards.repository.FlashcardRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DeckFacadeImpl implements DeckFacade {

	FlashcardRepository flashcardRepository;

	@Override
	public Set<FlashcardDto> getCardPortion(long deckId) {
		Pageable portionRequest = PageRequest.of(0, 2);
		Collection<Flashcard> cardPortion = flashcardRepository.findAllByDeckId(deckId, portionRequest);
		return cardPortion.stream().map(FlashcardDtoConverter::convertToDto).collect(Collectors.toSet());
	}
}
