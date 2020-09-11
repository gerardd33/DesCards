package com.descards.flashcards.repository;

import com.descards.flashcards.model.entity.ApplicationUser;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long> {
}
