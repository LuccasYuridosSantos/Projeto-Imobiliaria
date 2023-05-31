package br.gov.sp.fatec.pi.imobiliaria.model.vo.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImobiliariaPutRequest {

  @NotBlank
  private String nome;
  private String telefone;
  private String email;
  private EnderecoRequest endereco;
}
