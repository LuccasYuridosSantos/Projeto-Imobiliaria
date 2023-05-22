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
    private AgenteImobiliario agenteImobiliario;
    @ManyToOne(cascade = CascadeType.ALL)
    private Imobiliaria imobiliaria;
    

	// Construtor da classe Imovel
	public Imovel(Long id, Endereco endereco, int numQuartos, int numBanheiros, int numVagasGaragem, double area,
			double valorVenda, double valorAluguel, TipoImovel tipoImovel, Cliente proprietario,
			AgenteImobiliario agenteImobiliario, Imobiliaria imobiliaria) {
		super();
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
		this.agenteImobiliario = agenteImobiliario;
		this.imobiliaria = imobiliaria;
	}	

	public Imovel() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public int getNumQuartos() {
		return numQuartos;
	}

	public void setNumQuartos(int numQuartos) {
		this.numQuartos = numQuartos;
	}

	public int getNumBanheiros() {
		return numBanheiros;
	}

	public void setNumBanheiros(int numBanheiros) {
		this.numBanheiros = numBanheiros;
	}

	public int getNumVagasGaragem() {
		return numVagasGaragem;
	}

	public void setNumVagasGaragem(int numVagasGaragem) {
		this.numVagasGaragem = numVagasGaragem;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public double getValorAluguel() {
		return valorAluguel;
	}

	public void setValorAluguel(double valorAluguel) {
		this.valorAluguel = valorAluguel;
	}

	public TipoImovel getTipoImovel() {
		return tipoImovel;
	}

	public void setTipoImovel(TipoImovel tipoImovel) {
		this.tipoImovel = tipoImovel;
	}

	public Cliente getProprietario() {
		return proprietario;
	}

	public void setProprietario(Cliente proprietario) {
		this.proprietario = proprietario;
	}

	public AgenteImobiliario getAgenteImobiliario() {
		return agenteImobiliario;
	}

	public void setAgenteImobiliario(AgenteImobiliario agenteImobiliario) {
		this.agenteImobiliario = agenteImobiliario;
	}

	public Imobiliaria getImobiliaria() {
		return imobiliaria;
	}

	public void setImobiliaria(Imobiliaria imobiliaria) {
		this.imobiliaria = imobiliaria;
	}

}

