package br.gov.sp.fatec.pi.imobiliaria.repository;

import br.gov.sp.fatec.pi.imobiliaria.model.Imobiliaria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImobiliariaRepository extends JpaRepository<Imobiliaria, Long> {
}
