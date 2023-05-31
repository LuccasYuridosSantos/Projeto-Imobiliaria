package br.gov.sp.fatec.pi.imobiliaria.model.response;

import br.gov.sp.fatec.pi.imobiliaria.model.Endereco;
import br.gov.sp.fatec.pi.imobiliaria.model.TipoCliente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * A classe ClienteResponse representa a resposta de um cliente.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteResponse {

    /**
     * O ID do cliente.
     */
    private Long id;

    /**
     * O nome do cliente.
     */
    private String nome;

    /**
     * O endereço do cliente.
     */
    @JsonIgnore
    private Endereco endereco;

    /**
     * O telefone do cliente.
     */
    private String telefone;

    /**
     * O e-mail do cliente.
     */
    private String email;

    /**
     * O tipo do cliente.
     */
    private TipoCliente tipoCliente;

    /**
     * O CPF do cliente.
     */
    private String cpf;

    /**
     * O CNPJ do cliente.
     */
    private String cnpj;

    /**
     * A lista de imóveis associados ao cliente.
     */
    @JsonIgnore
    private List<ImovelResponse> imoveis;

    /**
     * O usuário associado ao cliente.
     */
    @JsonIgnore
    private UsuarioResponse usuario;
}
