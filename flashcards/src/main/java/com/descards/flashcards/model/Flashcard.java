package com.descards.flashcards.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Duration;

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

	private String back;

	private Duration repetitionInterval;

	public Flashcard(Deck deck, String front, String back, Duration repetitionInterval) {
		this.deck = deck;
		this.front = front;
		this.back = back;
		this.repetitionInterval = repetitionInterval;
	}
}
