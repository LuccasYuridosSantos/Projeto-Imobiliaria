package br.gov.sp.fatec.pi.imobiliaria.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * A classe Imobiliaria representa uma imobiliária.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Imobiliaria {

  /**
   * O ID da imobiliária.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * O nome da imobiliária.
   */
  private String nome;

  /**
   * O CNPJ da imobiliária.
   */
  @Column(unique = true)
  private String cnpj;

  /**
   * O telefone da imobiliária.
   */
  private String telefone;

  /**
   * O email da imobiliária.
   */
  @Column(unique = true)
  private String email;

  /**
   * O endereço da imobiliária.
   */
  @ManyToOne
  @JoinColumn(name = "endereco_id")
  private Endereco endereco;

  /**
   * A lista de corretores associados à imobiliária.
   */
  @OneToMany(mappedBy = "imobiliaria")
  private List<Corretor> corretores;

  /**
   * O usuário associado à imobiliária.
   */
  @OneToOne
  @JoinColumn(name = "usuario_id")
  private Usuario usuario;
}
