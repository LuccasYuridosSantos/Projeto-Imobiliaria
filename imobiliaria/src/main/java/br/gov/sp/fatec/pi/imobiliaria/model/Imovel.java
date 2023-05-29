package br.gov.sp.fatec.pi.imobiliaria.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Imovel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Endereco endereco;

	private int numQuartos;

	private int numBanheiros;

	private int numVagasGaragem;

	private BigDecimal area;

	private BigDecimal valorVenda;

	private BigDecimal valorAluguel;

	@Enumerated(EnumType.STRING)
	private TipoImovel tipoImovel;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Cliente proprietario;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Imobiliaria imobiliaria;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Cliente locatario;

	@OneToMany
	private List<Imagem> imagens;
}

