package br.gov.sp.fatec.pi.imobiliaria.repository;

import br.gov.sp.fatec.pi.imobiliaria.model.Corretor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de repositório para a entidade Corretor.
 */
@Repository
public interface CorretorRepository extends JpaRepository<Corretor, Long> {
}

