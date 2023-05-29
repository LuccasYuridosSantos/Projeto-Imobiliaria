package br.gov.sp.fatec.pi.imobiliaria.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Imobiliaria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  private String cnpj;

  private String telefone;

  private String email;

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Endereco endereco;

  @OneToMany(mappedBy = "imobiliaria", cascade = CascadeType.ALL)
  private List<Corretor> corretores;

  @OneToOne
  private Usuario usuario;
}
