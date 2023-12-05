package com.betrybe.agrix.controllers;

import com.betrybe.agrix.dto.AuthenticationDto;
import com.betrybe.agrix.dto.PersonDto;
import com.betrybe.agrix.dto.ResponseDto;
import com.betrybe.agrix.ebytr.staff.exception.InvalidCredentialException;
import com.betrybe.agrix.ebytr.staff.model.entity.Person;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import com.betrybe.agrix.ebytr.staff.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
  private final TokenService tokenService;
  private final AuthenticationManager authenticationManager;

  /**
  * Constructor.
  */
  public PersonController(PersonService personService, TokenService tokenService, 
        AuthenticationManager authenticationManager) {
    this.personService = personService;
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
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

  /**
  * Login.
  */
  @PostMapping("/auth/login")
  public ResponseEntity<?> login(@RequestBody AuthenticationDto authenticationDto) {
    try {

      UsernamePasswordAuthenticationToken authenticationToken = 
            new UsernamePasswordAuthenticationToken(authenticationDto.username(), 
            authenticationDto.password());
    
      Authentication authentication = authenticationManager.authenticate(authenticationToken);

      Person person = (Person) authentication.getPrincipal();

      String tokenResponse = tokenService.generateToken(person);

      return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(tokenResponse));

    } catch (Exception e) {
      throw new InvalidCredentialException();
    }
  }
}
