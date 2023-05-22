package br.gov.sp.fatec.pi.imobiliaria.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Imobiliaria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;
  private String cnpj;
  private String telefone;
  private String email;

  @OneToMany(mappedBy = "imobiliaria", cascade = CascadeType.ALL)
  private List<Corretor> corretores;


	public Imobiliaria() {
	}

	public Imobiliaria(final Long id, final String nome, final String cnpj, final String telefone, final String email, final List<Corretor> corretores) {
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.telefone = telefone;
		this.email = email;
		this.corretores = corretores;
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(final String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(final String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public List<Corretor> getCorretores() {
		return corretores;
	}

	public void setCorretores(final List<Corretor> corretores) {
		this.corretores = corretores;
	}
}
