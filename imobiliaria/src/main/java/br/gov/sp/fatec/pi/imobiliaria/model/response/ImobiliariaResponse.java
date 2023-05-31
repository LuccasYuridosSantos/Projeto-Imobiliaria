package br.gov.sp.fatec.pi.imobiliaria.model.response;

import br.gov.sp.fatec.pi.imobiliaria.model.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * A classe ImobiliariaResponse representa a resposta de uma imobiliária.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImobiliariaResponse {

  /**
   * O ID da imobiliária.
   */
  private Long id;

  /**
   * O nome da imobiliária.
   */
  private String nome;

  /**
   * O CNPJ da imobiliária.
   */
  private String cnpj;

  /**
   * O telefone da imobiliária.
   */
  private String telefone;

  /**
   * O e-mail da imobiliária.
   */
  private String email;

  /**
   * O endereço da imobiliária.
   */
  @JsonIgnore
  private Endereco endereco;

  /**
   * A lista de corretores associados à imobiliária.
   */
  @JsonIgnore
  private List<CorretorResponse> corretores;

  /**
   * O usuário associado à imobiliária.
   */
  @JsonIgnore
  private UsuarioResponse usuario;
}
