package com.descards.flashcards.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

	public Flashcard(Deck deck, String front, String back, Duration repetitionInterval) {
		this.deck = deck;
		this.front = front;
		this.back = back;
		this.repetitionInterval = repetitionInterval;
		this.createdTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
	}
}
