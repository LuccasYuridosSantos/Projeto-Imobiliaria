package br.gov.sp.fatec.pi.imobiliaria.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A classe Corretor representa um corretor imobiliário.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Corretor {

  /**
   * O ID do corretor.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * O nome do corretor.
   */
  private String nome;

  /**
   * O email do corretor.
   */
  @Column(unique = true)
  private String email;

  /**
   * O telefone do corretor.
   */
  private String telefone;

  /**
   * A imobiliária à qual o corretor está associado.
   */
  @ManyToOne
  @JoinColumn(name = "imobiliaria_id")
  private Imobiliaria imobiliaria;
}
