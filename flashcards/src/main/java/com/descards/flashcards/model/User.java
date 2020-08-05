package com.descards.flashcards.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	String name;

	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "user")
	Set<Deck> decks = new HashSet<>();

	public User(String name) {
		this.name = name;
	}
}
