package br.gov.sp.fatec.pi.imobiliaria.repository;

import br.gov.sp.fatec.pi.imobiliaria.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface de repositório para entidade Usuario.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

  /**
   * Busca um usuário pelo nome de usuário (ignorando maiúsculas e minúsculas).
   *
   * @param username  Nome de usuário
   * @return          Usuário encontrado (opcional)
   */
  Optional<Usuario> findUsuarioByUsernameIgnoreCase(final String username);

  /**
   * Busca um usuário pelo nome de usuário.
   *
   * @param username  Nome de usuário
   * @return          Usuário encontrado (opcional)
   */
  Optional<Usuario> findByUsername(final String username);
}

