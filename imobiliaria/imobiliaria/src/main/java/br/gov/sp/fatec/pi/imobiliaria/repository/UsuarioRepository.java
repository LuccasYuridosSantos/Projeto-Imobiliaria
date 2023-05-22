package br.gov.sp.fatec.pi.imobiliaria.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.gov.sp.fatec.pi.imobiliaria.model.Usuario;

/**
 * 
 * Esta interface representa o repositório de dados para a entidade Usuario,
 * permitindo operações CRUD (Create, Read, Update, Delete) através do uso de
 * métodos fornecidos pelo framework Spring Data. A interface CrudRepository é
 * uma interface genérica do Spring Data que já fornece as operações básicas de
 * CRUD.
 */
@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

}
