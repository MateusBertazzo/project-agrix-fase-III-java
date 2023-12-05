package com.betrybe.agrix.controllers;

import com.betrybe.agrix.dto.FertilizerDto;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.services.FertilizerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * Fertilizer controller.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {
  
  private final FertilizerService fertilizerService;

  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Create a new fertilizer.
   */
  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public FertilizerDto createFertilizer(@RequestBody FertilizerDto fertilizerDto) {
    Fertilizer newFertilizer = fertilizerService.createFertilizer(fertilizerDto.toFertilizer());

    FertilizerDto fertilizerToResponse = 
        new FertilizerDto(newFertilizer.getId(), newFertilizer.getName(), 
          newFertilizer.getBrand(), newFertilizer.getComposition());
    
    return fertilizerToResponse;
  }

  /**
   * Get all fertilizers.
   */
  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  @Secured("ROLE_ADMIN")
  public List<FertilizerDto> getAllFertilizers() {
    List<Fertilizer> fertilizers = fertilizerService.getAllFertilizers();
    List<FertilizerDto> fertilizersDto = fertilizers.stream()
        .map(fertilizer -> new FertilizerDto(fertilizer.getId(), fertilizer.getName(), 
          fertilizer.getBrand(), fertilizer.getComposition())).toList();

    return fertilizersDto;
  }

  /**
   * Get a fertilizer by id.
   */
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public FertilizerDto getFertilizerById(@PathVariable Long id) {
    Fertilizer fertilizer = fertilizerService.getFarmById(id);
    FertilizerDto fertilizerDto = new FertilizerDto(fertilizer.getId(), fertilizer.getName(), 
          fertilizer.getBrand(), fertilizer.getComposition());

    return fertilizerDto;
  }
}
