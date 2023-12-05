package com.betrybe.agrix.models.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;


/**
 * Crop entity.
 */
@Data
@Entity
@Table(name = "crops")
public class Crop {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)  
  private Long id;
  private String name;

  @ManyToOne
  @JoinColumn(name = "farm_id")
  private Farm farm;

  @Column(name = "planted_area")
  private Double plantedArea;

  @Column(name = "planted_date")
  private LocalDate plantedDate;

  @Column(name = "harvest_date")
  private LocalDate harvestDate;

  @ManyToMany
  @JoinTable(name = "crops_fertilizers",
      joinColumns = @JoinColumn(name = "crop_id"),
      inverseJoinColumns = @JoinColumn(name = "fertilizer_id"))
  private List<Fertilizer> fertilizers;

  public Crop() {
  }

  /**
  * Crop constructor.
  */
  public Crop(Long id, String name, Farm farm, Double plantedArea, LocalDate plantedDate, 
      LocalDate harvestDate) {
    this.id = id;
    this.name = name;
    this.farm = farm;
    this.plantedArea = plantedArea;
    this.plantedDate = plantedDate;
    this.harvestDate = harvestDate;
  }
}
