package br.gov.sp.fatec.pi.imobiliaria.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
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
}
