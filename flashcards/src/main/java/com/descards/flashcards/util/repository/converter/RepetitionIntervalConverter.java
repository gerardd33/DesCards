package com.descards.flashcards.util.repository.converter;

import com.descards.flashcards.model.nonentity.RepetitionInterval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Component
@Converter(autoApply = true)
public class RepetitionIntervalConverter implements AttributeConverter<RepetitionInterval, Long> {

	private static RepetitionInterval repetitionInterval;

	@Autowired
	public void setRepetitionInterval(RepetitionInterval repetitionInterval) {
		RepetitionIntervalConverter.repetitionInterval = repetitionInterval;
	}

	@Override
	public Long convertToDatabaseColumn(RepetitionInterval interval) {
		return interval.getCurrent().toMinutes();
	}

	@Override
	public RepetitionInterval convertToEntityAttribute(Long minutes) {
		RepetitionInterval newRepetitionInterval = repetitionInterval.copy();
		newRepetitionInterval.setFromMinutes(minutes);
		return newRepetitionInterval;
	}
}
