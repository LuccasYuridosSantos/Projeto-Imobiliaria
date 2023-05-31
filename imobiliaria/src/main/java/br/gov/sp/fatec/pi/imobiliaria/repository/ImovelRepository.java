package br.gov.sp.fatec.pi.imobiliaria.repository;

import br.gov.sp.fatec.pi.imobiliaria.model.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de repositório para a entidade Imovel.
 */
@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Long> {}
