package com.descards.flashcards.util;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Slf4j
@Converter(autoApply = true)
public class DurationConverter implements AttributeConverter<Duration, Long> {

	@Override
	public Long convertToDatabaseColumn(Duration attribute) {
		log.info("Convert to Long");
		return attribute.toNanos();
	}

	@Override
	public Duration convertToEntityAttribute(Long dbData) {
		log.info("Convert to Duration");
		return Duration.of(dbData, ChronoUnit.NANOS);
	}
}
