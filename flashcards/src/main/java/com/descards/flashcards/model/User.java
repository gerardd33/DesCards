package com.descards.flashcards.model;

import lombok.Data;
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

	@OneToMany(mappedBy = "user")
	Set<Deck> decks = new HashSet<>();
}
