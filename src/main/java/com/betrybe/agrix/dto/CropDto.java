package com.betrybe.agrix.dto;


import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import java.time.LocalDate;

/**
* Crop DTO.
*/
public record CropDto(Long id, String name, Long farmId, Double plantedArea, 
    LocalDate plantedDate, LocalDate harvestDate) {
  
  /**
  * Crop DTO.
  */
  public Crop toCrop() {
    Farm farm = new Farm();
    farm.setId(farmId);
    return new Crop(id, name, farm, plantedArea, plantedDate, harvestDate);
  }
}
