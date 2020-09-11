package com.descards.flashcards.util.repository.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Duration;

@Converter(autoApply = true)
public class DurationConverter implements AttributeConverter<Duration, Long> {

	@Override
	public Long convertToDatabaseColumn(Duration attribute) {
		return attribute.toMinutes();
	}

	@Override
	public Duration convertToEntityAttribute(Long minutes) {
		return Duration.ofMinutes(minutes);
	}
}
