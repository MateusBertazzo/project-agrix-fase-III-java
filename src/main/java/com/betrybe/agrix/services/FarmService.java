package com.betrybe.agrix.services;

import com.betrybe.agrix.exceptions.FarmNotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FarmRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Farm service.
 */
@Service
public class FarmService {
  private final FarmRepository farmRepository;
  private final CropRepository cropRepository;

  @Autowired
  public FarmService(FarmRepository farmRepository, CropRepository cropRepository) {
    this.farmRepository = farmRepository;
    this.cropRepository = cropRepository;
  }

  public Farm createFarm(Farm farm) {
    return farmRepository.save(farm);
  }
  
  public List<Farm> getAllFarm() {
    return farmRepository.findAll();
  }


  /**
   * Get farm by id.
   */
  public Farm getFarmById(Long id) {
    return farmRepository.findById(id)
        .orElseThrow(() -> new FarmNotFoundException());
    // Optional<Farm> farm = farmRepository.findById(id);
    // if (farm.isEmpty()) {
    //   throw new FarmNotFoundException();
    // }
    // return farm.get();
  }

  /**
   * Create a crop.
   */
  public Crop createCrop(Long farmId, Crop crop) {
    Farm farm = getFarmById(farmId);
    
    crop.setFarm(farm);

    Crop cropCreated = cropRepository.save(crop);


    farm.getCrops().add(cropCreated);

    updateFarm(farm);

    return cropCreated;
  }

  private void updateFarm(Farm farm) {
    farmRepository.save(farm);
  }

  /**
   * Get all crops from a farm.
   */
  public List<Crop> getAllCropsFromFarm(Long farmId) {
    Farm farm = getFarmById(farmId);
    return farm.getCrops();
  }  
}
