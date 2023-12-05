package com.betrybe.agrix.dto;

import com.betrybe.agrix.models.entities.Fertilizer;

/**
 * Fertilizer DTO.
 */
public record FertilizerDto(Long id, String name, String brand, String composition) {
  
  public Fertilizer toFertilizer() {
    return new Fertilizer(id, name, brand, composition);
  }

  public static FertilizerDto fromFertilizer(Fertilizer fertilizer) {
    return new FertilizerDto(fertilizer.getId(), fertilizer.getName(), 
        fertilizer.getBrand(), fertilizer.getComposition());
  }
}
