package br.gov.sp.fatec.pi.imobiliaria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String rua;

	private Integer numero;


	private String complemento;


	private String bairro;


	private String cidade;

	private String estado;

	private String cep;


  public Endereco() {
  }

  public Endereco(final Long id, final String rua, final int numero, final String complemento, final String bairro, final String cidade, final String estado, final String cep) {
    this.id = id;
    this.rua = rua;
    this.numero = numero;
    this.complemento = complemento;
    this.bairro = bairro;
    this.cidade = cidade;
    this.estado = estado;
    this.cep = cep;
  }

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getRua() {
    return rua;
  }

  public void setRua(final String rua) {
    this.rua = rua;
  }

  public int getNumero() {
    return numero;
  }

  public void setNumero(final int numero) {
    this.numero = numero;
  }

  public String getComplemento() {
    return complemento;
  }

  public void setComplemento(final String complemento) {
    this.complemento = complemento;
  }

  public String getBairro() {
    return bairro;
  }

  public void setBairro(final String bairro) {
    this.bairro = bairro;
  }

  public String getCidade() {
    return cidade;
  }

  public void setCidade(final String cidade) {
    this.cidade = cidade;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(final String estado) {
    this.estado = estado;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(final String cep) {
    this.cep = cep;
  }

  @Override
  public boolean equals(final Object o) {

    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final Endereco endereco = (Endereco) o;
    return numero == endereco.numero && Objects.equals(id, endereco.id) && Objects.equals(rua, endereco.rua) && Objects.equals(complemento, endereco.complemento) && Objects.equals(bairro, endereco.bairro) && Objects.equals(cidade, endereco.cidade) && Objects.equals(estado, endereco.estado) && Objects.equals(cep, endereco.cep);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, rua, numero, complemento, bairro, cidade, estado, cep);
  }

  @Override
  public String toString() {
    return "Endereco{" +
          "id=" + id +
          ", rua='" + rua + '\'' +
          ", numero=" + numero +
          ", complemento='" + complemento + '\'' +
          ", bairro='" + bairro + '\'' +
          ", cidade='" + cidade + '\'' +
          ", estado='" + estado + '\'' +
          ", cep='" + cep + '\'' +
          '}';
  }
}
