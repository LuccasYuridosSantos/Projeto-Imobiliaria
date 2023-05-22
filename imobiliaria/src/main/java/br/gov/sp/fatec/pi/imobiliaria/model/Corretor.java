package br.gov.sp.fatec.pi.imobiliaria.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Corretor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private String email;
  private String telefone;

	@ManyToOne(cascade ={CascadeType.PERSIST, CascadeType.MERGE})
  private Imobiliaria imobiliaria;

  public Corretor() {
  }

  public Corretor(final Long id, final String nome, final String email, final String telefone, final Imobiliaria imobiliaria) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.telefone = telefone;
    this.imobiliaria = imobiliaria;
  }

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(final String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(final String telefone) {
    this.telefone = telefone;
  }

  public Imobiliaria getImobiliaria() {
    return imobiliaria;
  }

  public void setImobiliaria(final Imobiliaria imobiliaria) {
    this.imobiliaria = imobiliaria;
  }
}

