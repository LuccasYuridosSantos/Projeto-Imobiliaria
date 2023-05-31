package br.gov.sp.fatec.pi.imobiliaria.repository;

import br.gov.sp.fatec.pi.imobiliaria.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Esta interface representa o repositório de dados para a entidade Cliente,
 * permitindo operações CRUD (Create, Read, Update, Delete) através do uso de
 * métodos fornecidos pelo framework Spring Data. A interface JpaRepository é
 * uma interface genérica do Spring Data que já fornece as operações básicas de
 * CRUD.
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
