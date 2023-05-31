package br.gov.sp.fatec.pi.imobiliaria.model.request;

import br.gov.sp.fatec.pi.imobiliaria.model.StatusCliente;
import br.gov.sp.fatec.pi.imobiliaria.model.TipoCliente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * A classe ClienteRequest representa a solicitação de criação de um cliente.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClienteRequest {

  /**
   * O nome do cliente.
   */
  @NotBlank
  private String nome;

  /**
   * O CPF do cliente.
   */
  private String cpf;

  /**
   * O CNPJ do cliente.
   */
  private String cnpj;

  /**
   * O endereço do cliente.
   */
  @Valid
  private EnderecoRequest endereco;

  /**
   * O telefone do cliente.
   */
  private String telefone;

  /**
   * O e-mail do cliente.
   */
  private String email;

  /**
   * O tipo do cliente (pessoa física ou pessoa jurídica).
   */
  private TipoCliente tipoCliente;

  /**
   * O status do cliente.
   */
  private StatusCliente status;
}
