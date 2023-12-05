package com.betrybe.agrix.services;

import com.betrybe.agrix.exceptions.FertilizerNotFoundException;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Fertilizer service.
 */
@Service
public class FertilizerService {
  private final FertilizerRepository fertilizerRepository;
  
  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  public Fertilizer createFertilizer(Fertilizer fertilizerDto) {
    return fertilizerRepository.save(fertilizerDto);
  }

  public List<Fertilizer> getAllFertilizers() {
    return fertilizerRepository.findAll();
  }

  public Fertilizer getFarmById(Long id) {
    return fertilizerRepository.findById(id)
        .orElseThrow(() -> new FertilizerNotFoundException());
  }
}
