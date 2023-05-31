package br.gov.sp.fatec.pi.imobiliaria.repository;

import br.gov.sp.fatec.pi.imobiliaria.model.Imobiliaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de repositório para a entidade Imobiliaria.
 */
@Repository
public interface ImobiliariaRepository extends JpaRepository<Imobiliaria, Long> {
}

