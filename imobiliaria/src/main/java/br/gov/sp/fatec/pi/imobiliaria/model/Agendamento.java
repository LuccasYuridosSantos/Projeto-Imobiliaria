package br.gov.sp.fatec.pi.imobiliaria.model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Agendamento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDateTime dataHora;

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Cliente cliente;

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Imovel imovel;

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Imobiliaria imobiliaria;

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Corretor corretor;

  private String descricao;

  public Agendamento() {
  }

  public Agendamento(final Long id, final LocalDateTime dataHora, final Cliente cliente, final Imovel imovel, final Imobiliaria imobiliaria, final Corretor corretor, final String descricao) {
    this.id = id;
    this.dataHora = dataHora;
    this.cliente = cliente;
    this.imovel = imovel;
    this.imobiliaria = imobiliaria;
    this.corretor = corretor;
    this.descricao = descricao;
  }

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public LocalDateTime getDataHora() {
    return dataHora;
  }

  public void setDataHora(final LocalDateTime dataHora) {
    this.dataHora = dataHora;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(final Cliente cliente) {
    this.cliente = cliente;
  }

  public Imovel getImovel() {
    return imovel;
  }

  public void setImovel(final Imovel imovel) {
    this.imovel = imovel;
  }

  public Imobiliaria getImobiliaria() {
    return imobiliaria;
  }

  public void setImobiliaria(final Imobiliaria imobiliaria) {
    this.imobiliaria = imobiliaria;
  }

  public Corretor getCorretor() {
    return corretor;
  }

  public void setCorretor(final Corretor corretor) {
    this.corretor = corretor;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(final String descricao) {
    this.descricao = descricao;
  }
}
