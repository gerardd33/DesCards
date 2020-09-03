package com.descards.flashcards.repository;

import com.descards.flashcards.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
