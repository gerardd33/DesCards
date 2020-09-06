package com.descards.flashcards.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeckDto {

	private Long id;

	private String name;

	private Long userId;
}
