package com.descards.flashcards.dto.converter;

import com.descards.flashcards.dto.UserDto;
import com.descards.flashcards.model.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserDtoConverter {

	public UserDto convertToDto(User user) {
		return UserDto.builder()
			.id(user.getId())
			.name(user.getName())
			.build();
	}
}
