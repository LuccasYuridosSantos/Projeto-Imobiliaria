package br.gov.sp.fatec.pi.imobiliaria.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A classe ImagemRequest representa a solicitação de uma imagem.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImagemRequest {

  /**
   * A URL da imagem.
   */
  @NotBlank
  private String url;
}
