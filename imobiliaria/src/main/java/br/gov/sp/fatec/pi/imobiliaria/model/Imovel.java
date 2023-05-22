package br.gov.sp.fatec.pi.imobiliaria.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Imovel {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade ={CascadeType.PERSIST, CascadeType.MERGE})
	private Endereco endereco;
	private int numQuartos;
	private int numBanheiros;
	private int numVagasGaragem;
	private double area;
	private double valorVenda;
	private double valorAluguel;
	private TipoImovel tipoImovel;
	@ManyToOne(cascade = CascadeType.ALL)
	private Cliente proprietario;
	@ManyToOne(cascade = CascadeType.ALL)
	private Corretor corretor;
	@ManyToOne(cascade = CascadeType.ALL)
	private Imobiliaria imobiliaria;
    

	// Construtor da classe Imovel

	public Imovel() {
	}

	public Imovel(final Long id, final Endereco endereco, final int numQuartos, final int numBanheiros, final int numVagasGaragem, final double area, final double valorVenda, final double valorAluguel, final TipoImovel tipoImovel, final Cliente proprietario, final Corretor corretor, final Imobiliaria imobiliaria) {
		this.id = id;
		this.endereco = endereco;
		this.numQuartos = numQuartos;
		this.numBanheiros = numBanheiros;
		this.numVagasGaragem = numVagasGaragem;
		this.area = area;
		this.valorVenda = valorVenda;
		this.valorAluguel = valorAluguel;
		this.tipoImovel = tipoImovel;
		this.proprietario = proprietario;
		this.corretor = corretor;
		this.imobiliaria = imobiliaria;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(final Endereco endereco) {
		this.endereco = endereco;
	}

	public int getNumQuartos() {
		return numQuartos;
	}

	public void setNumQuartos(final int numQuartos) {
		this.numQuartos = numQuartos;
	}

	public int getNumBanheiros() {
		return numBanheiros;
	}

	public void setNumBanheiros(final int numBanheiros) {
		this.numBanheiros = numBanheiros;
	}

	public int getNumVagasGaragem() {
		return numVagasGaragem;
	}

	public void setNumVagasGaragem(final int numVagasGaragem) {
		this.numVagasGaragem = numVagasGaragem;
	}

	public double getArea() {
		return area;
	}

	public void setArea(final double area) {
		this.area = area;
	}

	public double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(final double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public double getValorAluguel() {
		return valorAluguel;
	}

	public void setValorAluguel(final double valorAluguel) {
		this.valorAluguel = valorAluguel;
	}

	public TipoImovel getTipoImovel() {
		return tipoImovel;
	}

	public void setTipoImovel(final TipoImovel tipoImovel) {
		this.tipoImovel = tipoImovel;
	}

	public Cliente getProprietario() {
		return proprietario;
	}

	public void setProprietario(final Cliente proprietario) {
		this.proprietario = proprietario;
	}

	public Corretor getCorretor() {
		return corretor;
	}

	public void setCorretor(final Corretor corretor) {
		this.corretor = corretor;
	}

	public Imobiliaria getImobiliaria() {
		return imobiliaria;
	}

	public void setImobiliaria(final Imobiliaria imobiliaria) {
		this.imobiliaria = imobiliaria;
	}
}

