package br.gov.sp.fatec.pi.imobiliaria.model.vo.request;

import br.gov.sp.fatec.pi.imobiliaria.model.StatusCliente;
import br.gov.sp.fatec.pi.imobiliaria.model.TipoCliente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClienteRequest {

  private String nome;

  private String cpf;

  private String cnpj;

  @Valid
  private EnderecoRequest endereco;

  private String telefone;

  private String email;

  private TipoCliente tipoCliente;

  private StatusCliente status;
}
