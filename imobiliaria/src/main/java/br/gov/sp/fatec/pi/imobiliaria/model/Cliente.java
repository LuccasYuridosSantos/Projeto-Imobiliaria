package br.gov.sp.fatec.pi.imobiliaria.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * A classe Cliente representa um cliente da imobiliária.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    /**
     * O ID do cliente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * O nome do cliente.
     */
    private String nome;

    /**
     * O endereço do cliente.
     */
    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    /**
     * O telefone do cliente.
     */
    private String telefone;

    /**
     * O email do cliente.
     */
    private String email;

    /**
     * O tipo de cliente (pessoa física ou jurídica).
     */
    private TipoCliente tipoCliente;

    /**
     * O CPF do cliente (se for pessoa física).
     */
    private String cpf;

    /**
     * O CNPJ do cliente (se for pessoa jurídica).
     */
    private String cnpj;

    /**
     * A lista de imóveis associados ao cliente.
     */
    @OneToMany(mappedBy = "proprietario", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Imovel> imoveis;

    /**
     * O usuário associado ao cliente.
     */
    @OneToOne
    private Usuario usuario;
}
