package com.descards.flashcards.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Data
@NoArgsConstructor
public class Flashcard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "deck_id", nullable = false)
	private Deck deck;

	private String front;

	@EqualsAndHashCode.Exclude
	private String back;

	@EqualsAndHashCode.Exclude
	private Duration repetitionInterval;

	@EqualsAndHashCode.Exclude
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime createdDate;

	public Flashcard(Deck deck, String front, String back, Duration repetitionInterval) {
		this.deck = deck;
		this.front = front;
		this.back = back;
		this.repetitionInterval = repetitionInterval;
		this.createdDate = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
	}
}
