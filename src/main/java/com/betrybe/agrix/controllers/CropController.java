package com.betrybe.agrix.controllers;

import com.betrybe.agrix.dto.CropDtos;
import com.betrybe.agrix.dto.FertilizerDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.services.CropService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * Crop controller.
 */
@RestController
@RequestMapping("/crops")
public class CropController {
  private final CropService cropService;
  
  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;    
  }

  
  /**
  * Get all crops.
  */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
  public List<CropDtos> getAllCrop() {
    List<Crop> crops = cropService.getAllCrop();
    List<CropDtos> cropsDto = crops.stream()
        .map(crop -> 
            new CropDtos(
                crop.getId(), crop.getName(), crop.getFarm().getId(), 
                  crop.getPlantedArea(), crop.getPlantedDate(), crop.getHarvestDate()))
                  .toList();

    return cropsDto;
  }


  /**
  * Get By Id crop.
  */
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public CropDtos getCropById(@PathVariable Long id) {
    Crop crop = cropService.getCropById(id);
    CropDtos cropServiceResponse = 
        new CropDtos(crop.getId(), crop.getName(), crop.getFarm().getId(), crop.getPlantedArea(), 
            crop.getPlantedDate(), crop.getHarvestDate());

    return cropServiceResponse;
  }

  /** 
  * Get By Harvest Date Between crop. 
  */
  @GetMapping("/search")
  @ResponseStatus(HttpStatus.OK)
  public List<CropDtos> getCropByHarvestDateBetween(LocalDate start, LocalDate end) {
    List<Crop> crops = cropService.getCropByHarvestDateBetween(start, end);
    List<CropDtos> cropsDto = crops.stream()
        .map(crop -> 
            new CropDtos(
                crop.getId(), crop.getName(), crop.getFarm().getId(), 
                  crop.getPlantedArea(), crop.getPlantedDate(), crop.getHarvestDate()))
                  .toList();

    return cropsDto;
  }

  /** 
  * Add Fertilizer By/To Crop. 
  */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  @ResponseStatus(HttpStatus.CREATED)
  public String addFertilizerByCrop(@PathVariable Long cropId, @PathVariable Long fertilizerId) {
    cropService.addFertilizerByCrop(cropId, fertilizerId);

    return "Fertilizante e plantação associados com sucesso!";
  }

  /** 
  * Find Fertilizer By Crop Id. 
  */
  @GetMapping("/{cropId}/fertilizers")
  @ResponseStatus(HttpStatus.OK)
  public List<FertilizerDto> getAllFertilizersByCropId(@PathVariable Long cropId) {
    List<Fertilizer> fertilizers = cropService.getAllFertilizersByCropId(cropId);
    List<FertilizerDto> fertilizerDto = fertilizers.stream().map(fertilizer -> 
        new FertilizerDto(fertilizer.getId(), fertilizer.getName(), fertilizer.getBrand(),
            fertilizer.getComposition())).toList();

    return fertilizerDto;
  }
}
