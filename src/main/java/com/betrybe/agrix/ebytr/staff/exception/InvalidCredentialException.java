package com.betrybe.agrix.ebytr.staff.exception;

/**
 * Exception CredentialInvalid.
 */
public class InvalidCredentialException extends RuntimeException {

  public InvalidCredentialException() {
    super("Credenciais invalidas!");
  }
}
