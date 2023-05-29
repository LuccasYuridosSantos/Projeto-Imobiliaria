package br.gov.sp.fatec.pi.imobiliaria.model.vo.response;

import br.gov.sp.fatec.pi.imobiliaria.model.Endereco;
import br.gov.sp.fatec.pi.imobiliaria.model.TipoCliente;
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
public class ClienteResponse {

    private Long id;
    private String nome;
    @JsonIgnore
    private Endereco endereco;
    private String telefone;
    private String email;
    private TipoCliente tipoCliente;
    private String cpf;
    private String cnpj;
    @JsonIgnore
    private List<ImovelResponse> imoveis;
    @JsonIgnore
    private UsuarioResponse usuario;
}
