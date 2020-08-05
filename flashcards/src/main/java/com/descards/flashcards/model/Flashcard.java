package com.descards.flashcards.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Period;

@Entity
@Data
@NoArgsConstructor
public class Flashcard {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "deck_id", nullable = false)
	private Deck deck;

	private String front;

	private String back;

	private Period interval;
}
