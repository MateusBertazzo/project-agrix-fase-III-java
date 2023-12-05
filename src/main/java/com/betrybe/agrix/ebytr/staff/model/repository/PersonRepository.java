package com.betrybe.agrix.ebytr.staff.model.repository;

import com.betrybe.agrix.ebytr.staff.model.entity.Person;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Person JPA repository.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

  Optional<Person> findByUsername(String username);
}
