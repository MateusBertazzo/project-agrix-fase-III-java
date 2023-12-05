package com.betrybe.agrix.exceptions;

/**
 * Fertilizer not found exception.
 */
public class FertilizerNotFoundException extends RuntimeException {
  public FertilizerNotFoundException() {
    super("Fertilizante não encontrado!");
  }
}
