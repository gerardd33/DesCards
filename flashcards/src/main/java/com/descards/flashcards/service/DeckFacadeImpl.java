package com.descards.flashcards.service;

import com.descards.flashcards.dto.FlashcardDto;
import com.descards.flashcards.dto.FlashcardPortionRequestDto;
import com.descards.flashcards.dto.converter.FlashcardDtoConverter;
import com.descards.flashcards.dto.converter.FlashcardPortionRequestDtoConverter;
import com.descards.flashcards.facade.DeckFacade;
import com.descards.flashcards.model.entity.Flashcard;
import com.descards.flashcards.model.nonpersistent.FlashcardPortionRequest;
import com.descards.flashcards.repository.FlashcardRepository;
import com.descards.flashcards.model.nonpersistent.SortingField;
import com.descards.flashcards.model.nonpersistent.SortingDirection;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DeckFacadeImpl implements DeckFacade {

	FlashcardRepository flashcardRepository;

	@Override
	public List<FlashcardDto> getCardPortion(long deckId, FlashcardPortionRequestDto requestDto) {
		FlashcardPortionRequest request = FlashcardPortionRequestDtoConverter.convertFromDto(requestDto);

		System.out.println(request.getSortBy().getAttributeName());
		Pageable criteria;
		if (request.getSortingDirection() == SortingDirection.DESCENDING) {
			criteria = PageRequest.of(request.getOffset(), request.getLimit(),
					Sort.by(request.getSortBy().getAttributeName()).descending());
		} else {
			criteria = PageRequest.of(request.getOffset(), request.getLimit(),
					Sort.by(request.getSortBy().getAttributeName()).ascending());
		}

		Collection<Flashcard> cardPortion = flashcardRepository.findAllByDeckId(deckId, criteria);
		return cardPortion.stream().map(FlashcardDtoConverter::convertToDto).collect(Collectors.toList());
	}
}
