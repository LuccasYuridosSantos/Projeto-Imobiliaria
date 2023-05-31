package br.gov.sp.fatec.pi.imobiliaria.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A classe CorretorResponse representa a resposta de um corretor.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CorretorResponse {

  /**
   * O ID do corretor.
   */
  private Long id;

  /**
   * O nome do corretor.
   */
  private String nome;

  /**
   * O e-mail do corretor.
   */
  private String email;

  /**
   * O telefone do corretor.
   */
  private String telefone;

  /**
   * A imobiliária associada ao corretor.
   */
  @JsonIgnore
  private ImobiliariaResponse imobiliaria;
}
