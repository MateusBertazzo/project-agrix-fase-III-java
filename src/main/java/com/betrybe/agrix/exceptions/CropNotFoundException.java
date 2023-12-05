package com.betrybe.agrix.exceptions;


/**
 * Crop not found exception.
 */
public class CropNotFoundException extends RuntimeException {
  public CropNotFoundException() {
    super("Plantação não encontrada!");
  }
}
