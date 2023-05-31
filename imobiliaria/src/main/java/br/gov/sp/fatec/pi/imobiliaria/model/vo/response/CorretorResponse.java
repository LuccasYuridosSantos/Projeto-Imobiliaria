package br.gov.sp.fatec.pi.imobiliaria.model.vo.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CorretorResponse {

  private Long id;

  private String nome;

  private String email;

  private String telefone;

  @JsonIgnore
  private ImobiliariaResponse imobiliaria;

}
