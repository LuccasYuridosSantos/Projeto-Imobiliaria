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
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Cliente cliente;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Imovel imovel;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Imobiliaria imobiliaria;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private AgenteImobiliario agenteImobiliario;
    private String descricao;
    
    public Agendamento(Long id, LocalDateTime dataHora, Cliente cliente, Imovel imovel, Imobiliaria imobiliaria, AgenteImobiliario agenteImobiliario, String descricao) {
        this.id = id;
        this.dataHora = dataHora;
        this.cliente = cliente;
        this.imovel = imovel;
        this.imobiliaria = imobiliaria;
        this.agenteImobiliario = agenteImobiliario;
        this.descricao = descricao;
    }
    
    public Agendamento() {
    }    
   

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public Imobiliaria getImobiliaria() {
        return imobiliaria;
    }

    public void setImobiliaria(Imobiliaria imobiliaria) {
        this.imobiliaria = imobiliaria;
    }

    public AgenteImobiliario getAgenteImobiliario() {
        return agenteImobiliario;
    }

    public void setAgenteImobiliario(AgenteImobiliario agenteImobiliario) {
        this.agenteImobiliario = agenteImobiliario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
