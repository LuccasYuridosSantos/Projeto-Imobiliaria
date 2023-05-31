package br.gov.sp.fatec.pi.imobiliaria.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * A classe Endereco representa um endereço.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Endereco {

	/**
	 * O ID do endereço.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	/**
	 * A rua do endereço.
	 */
	private String rua;

	/**
	 * O número do endereço.
	 */
	private Long numero;

	/**
	 * O complemento do endereço.
	 */
	private String complemento;

	/**
	 * O bairro do endereço.
	 */
	private String bairro;

	/**
	 * A cidade do endereço.
	 */
	private String cidade;

	/**
	 * O estado do endereço.
	 */
	private String estado;

	/**
	 * O CEP do endereço.
	 */
	private String cep;
}
