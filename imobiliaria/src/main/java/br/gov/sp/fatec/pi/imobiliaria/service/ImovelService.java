package br.gov.sp.fatec.pi.imobiliaria.service;

import br.gov.sp.fatec.pi.imobiliaria.model.Imovel;
import br.gov.sp.fatec.pi.imobiliaria.repository.ImovelRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ImovelService {

    private ImovelRepository repository;

    public ImovelService(ImovelRepository repository) {
        this.repository = repository;
    }

    public Page<Imovel> listarImoveis(final Pageable pageable) {
        return repository.findAll(pageable);
    }
}
