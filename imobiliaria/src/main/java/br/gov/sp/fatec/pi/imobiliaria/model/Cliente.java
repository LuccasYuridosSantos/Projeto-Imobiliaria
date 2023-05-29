package br.gov.sp.fatec.pi.imobiliaria.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Endereco endereco;

    private String telefone;

    private String email;

    private TipoCliente tipoCliente;

    private String cpf;

    private String cnpj;

    @OneToMany(mappedBy = "proprietario", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Imovel> imoveis;

    @OneToOne
    private Usuario usuario;
}
