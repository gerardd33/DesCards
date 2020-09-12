package com.descards.flashcards.repository;

import com.descards.flashcards.model.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
