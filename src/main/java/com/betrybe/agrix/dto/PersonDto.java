package com.betrybe.agrix.dto;

import com.betrybe.agrix.ebytr.staff.model.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * Person DTO.
 */
public record PersonDto(Role role, String username, Long id) {
  public static PersonDto from(Person person) {
    return new PersonDto(person.getRole(), person.getUsername(), person.getId());
  }
}
