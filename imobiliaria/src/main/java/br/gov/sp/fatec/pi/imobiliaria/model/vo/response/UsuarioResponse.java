package br.gov.sp.fatec.pi.imobiliaria.model.vo.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioResponse {

  @JsonProperty
  private Long id;

  @JsonIgnore
  private String name;

  @JsonProperty
  private String username;

  @JsonIgnore
  @JsonProperty
  private ClienteResponse cliente;

  @JsonIgnore
  @JsonProperty
  private ImobiliariaResponse imobiliaria;
}
