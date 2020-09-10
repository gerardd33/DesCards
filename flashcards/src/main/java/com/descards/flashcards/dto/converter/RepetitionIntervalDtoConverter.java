package com.descards.flashcards.dto.converter;

import com.descards.flashcards.dto.RepetitionIntervalDto;
import com.descards.flashcards.model.nonentity.RepetitionInterval;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RepetitionIntervalDtoConverter {

	public RepetitionIntervalDto convertToDto(RepetitionInterval interval) {
		return RepetitionIntervalDto.builder()
				.current(interval.getCurrent().toMinutes())
				.again(interval.getAgain().toMinutes())
				.hard(interval.getHard().toMinutes())
				.ok(interval.getOk().toMinutes())
				.easy(interval.getEasy().toMinutes())
				.build();
	}
}
