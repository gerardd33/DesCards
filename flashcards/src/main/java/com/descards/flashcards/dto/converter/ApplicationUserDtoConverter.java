package com.descards.flashcards.dto.converter;

import com.descards.flashcards.dto.ApplicationUserDto;
import com.descards.flashcards.model.ApplicationUser;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ApplicationUserDtoConverter {

	public ApplicationUserDto convertToDto(ApplicationUser user) {
		return ApplicationUserDto.builder()
				.id(user.getId())
				.name(user.getName())
				.build();
	}
}
