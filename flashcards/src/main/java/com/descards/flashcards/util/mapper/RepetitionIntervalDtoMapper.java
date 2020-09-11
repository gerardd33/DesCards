package com.descards.flashcards.util.mapper;

import com.descards.flashcards.api.dto.RepetitionIntervalDto;
import com.descards.flashcards.model.nonentity.RepetitionInterval;

public class RepetitionIntervalDtoMapper {

	public static RepetitionIntervalDto mapToDto(RepetitionInterval interval) {
		return RepetitionIntervalDto.builder()
				.current(interval.getCurrent().toMinutes())
				.again(interval.getAgain().toMinutes())
				.hard(interval.getHard().toMinutes())
				.ok(interval.getOk().toMinutes())
				.easy(interval.getEasy().toMinutes())
				.build();
	}
}
