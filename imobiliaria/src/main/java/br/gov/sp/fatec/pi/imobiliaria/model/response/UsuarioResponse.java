package br.gov.sp.fatec.pi.imobiliaria.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A classe UsuarioResponse representa a resposta de um usuário.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioResponse {

  /**
   * O ID do usuário.
   */
  @JsonProperty
  private Long id;

  /**
   * O nome do usuário.
   */
  @JsonIgnore
  private String name;

  /**
   * O nome de usuário do usuário.
   */
  @JsonProperty
  private String username;

  /**
   * O cliente associado ao usuário.
   */
  @JsonIgnore
  @JsonProperty
  private ClienteResponse cliente;

  /**
   * A imobiliária associada ao usuário.
   */
  @JsonIgnore
  @JsonProperty
  private ImobiliariaResponse imobiliaria;
  
  /**
   * O token do usuário.
   */
  @JsonIgnore
  @JsonProperty
  private String token;
}
