package com.descards.flashcards.model.entity;

import com.descards.flashcards.model.nonentity.RepetitionInterval;
import com.descards.flashcards.util.database.converter.RepetitionIntervalConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Flashcard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "deck_id", nullable = false)
	@EqualsAndHashCode.Include
	private Deck deck;

	@EqualsAndHashCode.Include
	private String front;

	private String back;

	@Convert(converter = RepetitionIntervalConverter.class)
	private RepetitionInterval interval;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime createdTime;

	public Flashcard() {
		this.createdTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
	}

	public Flashcard(RepetitionInterval interval) {
		this();
		this.interval = interval;
	}

	public Flashcard(RepetitionInterval interval, String front, String back) {
		this(interval);
		this.front = front;
		this.back = back;
	}
}
