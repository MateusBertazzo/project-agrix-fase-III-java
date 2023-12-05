package com.betrybe.agrix.models.entities;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;


/**
 * Fertilizer entity.
 */
@Data
@Entity
@Table(name = "fertilizers")
public class Fertilizer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)  
  private Long id;

  private String name;

  private String brand;

  private String composition;
  
  @ManyToMany(mappedBy = "fertilizers")
  private List<Crop> crops;

  public Fertilizer() {
  }

  /**
  * Fetilizer constructor.
  */
  public Fertilizer(Long id, String name, String brand, String composition) {
    this.id = id;
    this.name = name;
    this.brand = brand;
    this.composition = composition;
  }
}
