package br.gov.sp.fatec.pi.imobiliaria.model.vo.request;

import br.gov.sp.fatec.pi.imobiliaria.model.Usuario;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImobiliariaRequest {

  @NotBlank
  private String nome;
  @NotBlank
  private String cnpj;
  private String telefone;
  private String email;
  @NotNull
  private EnderecoRequest endereco;
  private List<CorretorRequest> corretores;
  private Usuario usuario;
}
