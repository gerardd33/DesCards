package com.descards.flashcards.util;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Converter(autoApply = true)
public class DurationConverter implements AttributeConverter<Duration, Long> {

	@Override
	public Long convertToDatabaseColumn(Duration attribute) {
		return attribute.toMinutes();
	}

	@Override
	public Duration convertToEntityAttribute(Long dbData) {
		return Duration.of(dbData, ChronoUnit.MINUTES);
	}
}
