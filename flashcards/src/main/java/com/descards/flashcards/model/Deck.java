package com.descards.flashcards.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Deck {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	@OneToMany(mappedBy = "deck")
	private Set<Flashcard> cards = new HashSet<>();

	public Deck(String name, User user) {
		this.name = name;
		this.user = user;
	}
}
