package br.gov.sp.fatec.pi.imobiliaria.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A classe Imagem representa uma imagem relacionada a um imóvel.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Imagem {

  /**
   * O ID da imagem.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * A URL da imagem.
   */
  private String url;
}
