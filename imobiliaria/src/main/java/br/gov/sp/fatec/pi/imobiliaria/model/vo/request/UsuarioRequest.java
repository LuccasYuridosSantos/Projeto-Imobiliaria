package br.gov.sp.fatec.pi.imobiliaria.model.vo.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioRequest {

  @NotBlank
  @JsonProperty
  private String name;

  @NotBlank
  @JsonProperty
  private String username;

  @NotBlank
  @JsonProperty
  private String password;

  private ImobiliariaRequest imobiliaria;


  private ClienteRequest cliente;
}
