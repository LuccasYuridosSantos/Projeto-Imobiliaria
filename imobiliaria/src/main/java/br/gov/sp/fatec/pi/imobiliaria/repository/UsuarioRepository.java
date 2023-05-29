package br.gov.sp.fatec.pi.imobiliaria.repository;

import br.gov.sp.fatec.pi.imobiliaria.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 
 * Esta interface representa o repositório de dados para a entidade Usuario,
 * permitindo operações CRUD (Create, Read, Update, Delete) através do uso de
 * métodos fornecidos pelo framework Spring Data. A interface CrudRepository é
 * uma interface genérica do Spring Data que já fornece as operações básicas de
 * CRUD.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

  Optional<Usuario> findUsuarioByUsernameIgnoreCase(final String username);

}
