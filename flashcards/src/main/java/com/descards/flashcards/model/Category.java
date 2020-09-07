package com.descards.flashcards.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ElementCollection
	@EqualsAndHashCode.Exclude
	private List<String> specialFields = new ArrayList<>();

	public Category(String name) {
		this.name = name;
	}
}