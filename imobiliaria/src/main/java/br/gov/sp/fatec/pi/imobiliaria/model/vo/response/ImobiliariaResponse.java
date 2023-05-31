package br.gov.sp.fatec.pi.imobiliaria.model.vo.response;

import br.gov.sp.fatec.pi.imobiliaria.model.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImobiliariaResponse {

  private Long id;

  private String nome;

  private String cnpj;

  private String telefone;

  private String email;

  @JsonIgnore
  private Endereco endereco;

  @JsonIgnore
  private List<CorretorResponse> corretores;

  @JsonIgnore
  private UsuarioResponse usuario;
}
