package com.descards.flashcards.util;

import com.descards.flashcards.model.nonentity.RepetitionInterval;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Converter(autoApply = true)
public class RepetitionIntervalConverter implements AttributeConverter<RepetitionInterval, Long> {

	@Override
	public Long convertToDatabaseColumn(RepetitionInterval interval) {
		return null;
	}

	@Override
	public RepetitionInterval convertToEntityAttribute(Long minutes) {
		return null;
	}
}
