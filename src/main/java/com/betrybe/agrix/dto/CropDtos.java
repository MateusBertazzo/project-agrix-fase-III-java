package com.betrybe.agrix.dto;

import java.time.LocalDate;

/**
 * Crop DTO.
 */
public record CropDtos(Long id, String name, Long farmId, Double plantedArea, 
    LocalDate plantedDate, LocalDate harvestDate) {    
}

