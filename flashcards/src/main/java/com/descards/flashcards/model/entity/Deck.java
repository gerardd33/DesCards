package com.descards.flashcards.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Deck {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@EqualsAndHashCode.Include
	private String name;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@EqualsAndHashCode.Include
	private ApplicationUser user;

	@ToString.Exclude
	@OneToMany(mappedBy = "deck")
	private Set<Flashcard> cards = new HashSet<>();

	public Deck(String name, ApplicationUser user) {
		this.name = name;
		this.user = user;
	}
}
