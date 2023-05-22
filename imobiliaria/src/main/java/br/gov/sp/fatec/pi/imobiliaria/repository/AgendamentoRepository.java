package br.gov.sp.fatec.pi.imobiliaria.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.sp.fatec.pi.imobiliaria.model.Agendamento;


/**
 * 
 * Esta interface representa o repositório de dados para a entidade Agendamento,
 * permitindo operações CRUD (Create, Read, Update, Delete) através do uso de
 * métodos fornecidos pelo framework Spring Data. A interface CrudRepository é
 * uma interface genérica do Spring Data que já fornece as operações básicas de
 * CRUD.
 */
@Repository
public interface AgendamentoRepository extends CrudRepository<Agendamento, Long>{

	List<Agendamento> findByDataHora(final LocalDateTime dataHora);

	List<Agendamento> findByClienteIdAndImovelId(final Long clienteId, final Long imovelId);

	@Query("SELECT a FROM Agendamento a WHERE a.dataHora >= :dataHora")
    List<Agendamento> findAllFromDataHora(@Param("dataHora") final LocalDateTime dataHora);

}
