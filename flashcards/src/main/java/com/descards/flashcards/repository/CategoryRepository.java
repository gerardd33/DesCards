package com.descards.flashcards.repository;

import com.descards.flashcards.model.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
