package com.descards.flashcards.model.entity;

import com.descards.flashcards.model.nonentity.RepetitionInterval;
import com.descards.flashcards.model.nonentity.SchedulingAlgorithm;
import com.descards.flashcards.model.nonentity.SpacedRepetitionAlgorithm;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Data
@NoArgsConstructor
@Component
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

	@Setter(AccessLevel.NONE)
	private RepetitionInterval interval;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime createdTime;

	public Flashcard(String front, String back) {
		this.front = front;
		this.back = back;
		this.createdTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
	}

	@Autowired
	public void setRepetitionInterval(RepetitionInterval interval) {
		this.interval = interval;
	}
}
