package com.descards.flashcards.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@ElementCollection
	private Set<String> specialFields = new HashSet<>();

	public Category(String name) {
		this.name = name;
	}
}