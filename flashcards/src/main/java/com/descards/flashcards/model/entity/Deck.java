package com.descards.flashcards.model.entity;

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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Deck {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@EqualsAndHashCode.Include
	private ApplicationUser user;

	@EqualsAndHashCode.Include
	private String name;

	@ToString.Exclude
	@OneToMany(mappedBy = "deck")
	private Set<Flashcard> cards = new HashSet<>();

	public Deck(String name) {
		this.name = name;
	}

	public Deck(String name, ApplicationUser user) {
		this(name);
		this.user = user;
	}
}
