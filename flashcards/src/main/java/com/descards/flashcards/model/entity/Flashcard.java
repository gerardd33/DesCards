package com.descards.flashcards.model.entity;

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

	private Duration repetitionInterval;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime createdTime;

	public Flashcard(String front, String back) {
		this.front = front;
		this.back = back;
		this.repetitionInterval = Duration.ZERO; // TODO constant from algorithm
		this.createdTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
	}

	public Flashcard(Deck deck, String front, String back) {
		this(front, back);
		this.deck = deck;
	}
}
