package br.gov.sp.fatec.pi.imobiliaria.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

/**
 * A classe Agendamento representa um agendamento de visita para um imóvel.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agendamento {

  /**
   * O ID do agendamento.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * A data e hora do agendamento.
   */
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime dataHora;

  /**
   * O cliente associado ao agendamento.
   */
  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;

  /**
   * O imóvel associado ao agendamento.
   */
  @ManyToOne
  @JoinColumn(name = "imovel_id")
  private Imovel imovel;

  /**
   * A imobiliária associada ao agendamento.
   */
  @ManyToOne
  @JoinColumn(name = "imobiliaria_id")
  private Imobiliaria imobiliaria;

  /**
   * O corretor associado ao agendamento.
   */
  @ManyToOne
  @JoinColumn(name = "corretor_id")
  private Corretor corretor;

  /**
   * A descrição do agendamento.
   */
  private String descricao;
}
