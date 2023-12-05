package com.betrybe.agrix.services;

import com.betrybe.agrix.dto.FertilizerDto;
import com.betrybe.agrix.exceptions.CropNotFoundException;
import com.betrybe.agrix.exceptions.FertilizerNotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Crop service.
 */
@Service
public class CropService {
  private final CropRepository cropRepository;
  private final FertilizerRepository fertilizerRepository;

  public CropService(CropRepository cropRepository, FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * Get All crops.
   */
  public List<Crop> getAllCrop() {
    return cropRepository.findAll();
  }

  /**
   * Get crop by id.
   */
  public Crop getCropById(Long id) {
    Optional<Crop> crop = cropRepository.findById(id);
    if (crop.isEmpty()) {
      throw new CropNotFoundException();
    }
    return crop.get();
  }

  /**
   * Get crop by harvestDateBetween.
   */
  public List<Crop> getCropByHarvestDateBetween(LocalDate start, LocalDate end) {
    List<Crop> crops = cropRepository.findByHarvestDateBetween(start, end);

    if (crops.isEmpty()) {
      throw new CropNotFoundException();
    }

    return crops;
  }

  /**
   * Add a Fertilizer By/To Crop.
   */
  public void addFertilizerByCrop(Long cropId, long fertilizerId) {
    Crop crop = cropRepository.findById(cropId).orElseThrow(() -> new CropNotFoundException());

    Fertilizer fertilizer = fertilizerRepository.findById(fertilizerId)
        .orElseThrow(() -> new FertilizerNotFoundException());

    fertilizer.getCrops().add(crop);
    crop.getFertilizers().add(fertilizer);

    fertilizerRepository.save(fertilizer);
  }

  /**
   * Get all fertilizers by crop id.
   */
  public List<Fertilizer> getAllFertilizersByCropId(long cropId) {
    cropRepository.findById(cropId).orElseThrow(() -> new CropNotFoundException());

    return fertilizerRepository.findFertilizerByCropId(cropId);  
  }
}
