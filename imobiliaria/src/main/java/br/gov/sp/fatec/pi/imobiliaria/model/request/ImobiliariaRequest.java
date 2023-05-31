package br.gov.sp.fatec.pi.imobiliaria.model.request;

import br.gov.sp.fatec.pi.imobiliaria.model.Usuario;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * A classe ImobiliariaRequest representa a solicitação de criação de uma imobiliária.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImobiliariaRequest {

  /**
   * O nome da imobiliária.
   */
  @NotBlank
  private String nome;

  /**
   * O CNPJ da imobiliária.
   */
  @NotBlank
  private String cnpj;

  /**
   * O telefone da imobiliária.
   */
  private String telefone;

  /**
   * O email da imobiliária.
   */
  private String email;

  /**
   * O endereço da imobiliária.
   */
  @NotNull
  private EnderecoRequest endereco;

  /**
   * A lista de corretores associados à imobiliária.
   */
  private List<CorretorRequest> corretores;

  /**
   * O usuário associado à imobiliária.
   */
  private Usuario usuario;
}
