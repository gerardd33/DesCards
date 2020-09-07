package com.descards.flashcards.repository;

import com.descards.flashcards.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	Category findByName(String name);
}
