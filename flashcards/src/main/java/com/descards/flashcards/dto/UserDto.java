package com.descards.flashcards.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

	private Long id;

	private String name;
}
