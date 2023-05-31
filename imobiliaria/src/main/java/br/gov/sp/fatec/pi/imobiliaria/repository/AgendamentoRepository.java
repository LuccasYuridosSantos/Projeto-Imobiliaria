package br.gov.sp.fatec.pi.imobiliaria.repository;

import br.gov.sp.fatec.pi.imobiliaria.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


/**
 * Esta interface representa o repositório de dados para a entidade Agendamento,
 * permitindo operações CRUD (Create, Read, Update, Delete) através do uso de
 * métodos fornecidos pelo framework Spring Data. A interface JpaRepository é
 * uma interface genérica do Spring Data que já fornece as operações básicas de
 * CRUD.
 */
@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

	/**
	 * Retorna uma lista de agendamentos com base na data e hora especificadas.
	 *
	 * @param dataHora A data e hora para filtrar os agendamentos
	 * @return A lista de agendamentos encontrados
	 */
	List<Agendamento> findByDataHora(final LocalDateTime dataHora);

	/**
	 * Retorna uma lista de agendamentos com base no ID do cliente e do imóvel.
	 *
	 * @param clienteId O ID do cliente
	 * @param imovelId  O ID do imóvel
	 * @return A lista de agendamentos encontrados
	 */
	List<Agendamento> findByClienteIdAndImovelId(final Long clienteId, final Long imovelId);

	/**
	 * Retorna uma lista de agendamentos a partir da data e hora especificadas.
	 * A consulta considera apenas agendamentos cuja data e hora sejam maiores ou
	 * iguais à data e hora fornecidas.
	 *
	 * @param dataHora A data e hora de referência para buscar os agendamentos
	 * @return A lista de agendamentos encontrados
	 */
	@Query("SELECT a FROM Agendamento a WHERE a.dataHora >= :dataHora")
	List<Agendamento> findAllFromDataHora(@Param("dataHora") final LocalDateTime dataHora);

}

