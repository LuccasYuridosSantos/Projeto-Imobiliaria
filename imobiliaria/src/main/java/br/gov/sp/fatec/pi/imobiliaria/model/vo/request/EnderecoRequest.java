package br.gov.sp.fatec.pi.imobiliaria.model.vo.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnderecoRequest {

  @NotBlank
  @Size(max = 128)
  private String rua;

  @NotNull
  private Long numero;

  @NotBlank
  @Size(max = 128)
  private String complemento;


  @NotBlank
  @Size(max = 128)
  private String bairro;

  @NotBlank
  @Size(max = 128)
  private String cidade;

  @Size(max = 2, message = "ESTADO inválido, ex: SP")
  private String estado;

  @Size(max = 9, message = "CEP invalido")
  private String cep;
}
