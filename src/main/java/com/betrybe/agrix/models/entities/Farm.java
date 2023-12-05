package com.betrybe.agrix.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;

/**
 * Farm entity.
 */
@Data
@Entity
@Table(name = "farms")
public class Farm {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private Double size;

  @OneToMany(mappedBy = "farm")
  private List<Crop> crops;

  public Farm() {
  }

  /**
   * Farm constructor.
   */
  public Farm(Long id, String name, Double size) {
    this.id = id;
    this.name = name;
    this.size = size;
  }
}
