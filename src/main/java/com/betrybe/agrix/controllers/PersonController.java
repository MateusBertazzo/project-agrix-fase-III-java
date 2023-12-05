package com.betrybe.agrix.controllers;

import com.betrybe.agrix.dto.PersonDto;
import com.betrybe.agrix.ebytr.staff.model.entity.Person;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller Person.
 */
@RestController
@RequestMapping
public class PersonController {
  private final PersonService personService;

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  
  /**
  * Create a new person.
  */
  @PostMapping("/persons")
  @ResponseStatus(HttpStatus.CREATED)
  public PersonDto createPerson(@RequestBody Person person) {
    Person newPerson = personService.create(person);

    PersonDto personDtoResponse = new PersonDto(
        newPerson.getRole(), newPerson.getUsername(), newPerson.getId());
    
    return personDtoResponse;    
  }
}
