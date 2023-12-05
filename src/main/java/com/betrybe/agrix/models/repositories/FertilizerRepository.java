package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.Fertilizer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * Fertilizer repository.
 */
@Repository
public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {
  
    
  @Query("SELECT f from Fertilizer f JOIN f.crops c WHERE c.id = :cropId")
  List<Fertilizer> findFertilizerByCropId(Long cropId);
}
